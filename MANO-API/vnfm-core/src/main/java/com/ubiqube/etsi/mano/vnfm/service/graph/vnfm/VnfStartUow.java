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
package com.ubiqube.etsi.mano.vnfm.service.graph.vnfm;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.VnfNoopTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

public class VnfStartUow extends VnfAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public VnfStartUow() {
		super(new VnfNoopTask());
	}

	@Override
	public String exec(final VnfParameters params) {
		return null;
	}

	@Override
	public String rollback(final VnfParameters params) {
		return null;
	}

	@Override
	protected String getPrefix() {
		return "start";
	}

	@Override
	public List<WfDependency> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return new ArrayList<>();
	}

}
