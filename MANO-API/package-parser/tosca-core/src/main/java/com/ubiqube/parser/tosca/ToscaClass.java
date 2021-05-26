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
package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaClass extends ToscaBasePropertiesEntity {

	private Map<String, ValueObject> attributes;
	private String mimeType;
	private List<String> fileExt;
	private RequirementDefinition requirements;
	private InterfaceType interfaces;

	private Map<String, CapabilityDefinition> capabilities;
	private Map<String, Artifact> artifacts;
	// Used in relation ship only
	private List<String> valid_target_types;

	public void setMimeType(final String _mimeType) {
		mimeType = _mimeType;
	}

	public void setFileExt(final List<String> _fileExt) {
		fileExt = _fileExt;
	}

	public void setRequirement(final RequirementDefinition _requirements) {
		requirements = _requirements;
	}

	public void setCapabilities(final Map<String, CapabilityDefinition> _capabilities) {
		capabilities = _capabilities;

	}

	public void setArtifacts(final Map<String, Artifact> _artifacts) {
		artifacts = _artifacts;
	}

	public RequirementDefinition getRequirements() {
		return requirements;
	}

	public void setRequirements(final RequirementDefinition requirements) {
		this.requirements = requirements;
	}

	@JsonProperty("mime_type")
	public String getMimeType() {
		return mimeType;
	}

	@JsonProperty("file_ext")
	public List<String> getFileExt() {
		return fileExt;
	}

	public Map<String, CapabilityDefinition> getCapabilities() {
		return capabilities;
	}

	public Map<String, Artifact> getArtifacts() {
		return artifacts;
	}

	public InterfaceType getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final InterfaceType interfaces) {
		this.interfaces = interfaces;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public List<String> getValid_target_types() {
		return valid_target_types;
	}

	public void setValid_target_types(final List<String> valid_target_types) {
		this.valid_target_types = valid_target_types;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ToscaClass [");
		sb.append(super.toString());

		if (null != attributes) {
			sb.append(", attributes=" + attributes + ", ");
		}

		if (null != mimeType) {
			sb.append("mimeType=" + mimeType + ", ");
		}
		if (null != fileExt) {
			sb.append("fileExt=" + fileExt + ", ");
		}
		if (null != requirements) {
			sb.append("requirements=" + requirements + ", ");
		}
		if (null != interfaces) {
			sb.append("interfaces=" + interfaces + ", ");
		}
		if (null != capabilities) {
			sb.append("capabilities=" + capabilities + ", ");
		}
		if (null != artifacts) {
			sb.append("artifacts=" + artifacts + ", ");
		}

		if (null != valid_target_types) {
			sb.append("valid_target_types=" + valid_target_types + ", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
