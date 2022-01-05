/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.grammar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.Node.Operand;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class JsonFilter {

	private static final Logger LOG = LoggerFactory.getLogger(JsonFilter.class);

	private final JsonBeanUtil jsonBeanUtil;

	public JsonFilter(final JsonBeanUtil jsonBeanUtil) {
		super();
		this.jsonBeanUtil = jsonBeanUtil;
	}

	/**
	 *
	 * @param _object     An arbitraru not null object.
	 * @param _astBuilder An AST Builder.
	 * @return
	 */
	public boolean apply(@Nonnull final Object _object, @Nonnull final List<Node<String>> node) {
		final Node<String> selectedNode = node.stream()
				.filter(x -> !apply(_object, x))
				.findFirst()
				.orElse(null);
		return null == selectedNode;
	}

	private boolean apply(@Nonnull final Object _object, @Nonnull final Node<String> _node) {
		final Map<String, JsonBeanProperty> props = jsonBeanUtil.getProperties(_object);
		final JsonBeanProperty realProperty = props.get(_node.getName());
		if (realProperty == null) {
			LOG.warn("Object of class {} doesn't have a {} property.", _object.getClass(), _node.getName());
			return true;
		}
		final DocumentStatus documentStatus = invokeGetter(_object, realProperty, 0, _node);
		return documentStatus.getStatus() == DocumentStatus.Status.VALIDATED;
	}

	private static DocumentStatus invokeGetter(final Object _object, final JsonBeanProperty realProperty, final int index, final Node<String> _node) {
		final List<JsonBeanProperty> access = realProperty.getListAccessors();
		Object temp = _object;
		for (int i = index; i < access.size(); i++) {
			final JsonBeanProperty jsonBeanProperty = access.get(i);
			final Method readMethod = jsonBeanProperty.getPropertyDescriptor().getReadMethod();
			try {
				LOG.info("Invoking {} On {}", readMethod.getName(), temp.getClass());
				temp = readMethod.invoke(temp);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new GenericException(e);
			}
			if (temp == null) {
				return new DocumentStatus(DocumentStatus.Status.NOSTATE);
			}
			if (List.class.isAssignableFrom(temp.getClass())) {
				final List<?> list = (List<?>) temp;
				final DocumentStatus status = exploreList(list, realProperty, i, _node);
				if (status.getStatus() != DocumentStatus.Status.NOSTATE) {
					return status;
				}
			} else {
				// TODO: ???
			}
		}
		// Normally we should have the latest value.
		if (decide((String) temp, _node.getValue(), _node.getOp())) {
			return new DocumentStatus(DocumentStatus.Status.VALIDATED);
		}
		return new DocumentStatus(DocumentStatus.Status.REFUSED);
	}

	private static DocumentStatus exploreList(final List<?> list, final JsonBeanProperty realProperty, final int index, final Node<String> _node) {
		LOG.debug("Exploring sub list.");
		for (final Object object : list) {
			final DocumentStatus status = invokeGetter(object, realProperty, index + 1, _node);
			if (status.getStatus() == DocumentStatus.Status.NOSTATE) {
				continue;
			}
			return status;
		}
		return new DocumentStatus(DocumentStatus.Status.NOSTATE);
	}

	private static boolean decide(final String _objectValue, final String _value, final Operand _operand) {
		if (null == _operand) {
			return true;
		}
		if (Operand.EQ == _operand) {
			return _value.equals(_objectValue);
		}
		if (Operand.NEQ == _operand) {
			return !_value.equals(_objectValue);
		}
		// GT LT GTE LTE are numerical so cast everything in integer
		final int left = Integer.parseInt(_objectValue);
		final int right = Integer.parseInt(_value);
		if (Operand.GT == _operand) {
			return left > right;
		}
		if (Operand.LT == _operand) {
			return left < right;
		}
		if (Operand.GTE == _operand) {
			return left >= right;
		}
		if (Operand.LTE == _operand) {
			return left <= right;
		}
		return false;
	}
}
