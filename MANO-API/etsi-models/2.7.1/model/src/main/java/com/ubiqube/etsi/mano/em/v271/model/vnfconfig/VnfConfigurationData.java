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
package com.ubiqube.etsi.mano.em.v271.model.vnfconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents configuration parameters of a VNF instance.
 */
@ApiModel(description = "This type represents configuration parameters of a VNF instance. ")
@Validated

public class VnfConfigurationData {
	@JsonProperty("extCpConfig")
	@Valid
	private List<CpConfiguration> extCpConfig = null;

	@JsonProperty("dhcpServer")
	private String dhcpServer = null;

	@JsonProperty("vnfSpecificData")
	private Map<String, String> vnfSpecificData = null;

	public VnfConfigurationData extCpConfig(final List<CpConfiguration> extCpConfig) {
		this.extCpConfig = extCpConfig;
		return this;
	}

	public VnfConfigurationData addExtCpConfigItem(final CpConfiguration extCpConfigItem) {
		if (this.extCpConfig == null) {
			this.extCpConfig = new ArrayList<>();
		}
		this.extCpConfig.add(extCpConfigItem);
		return this;
	}

	/**
	 * Configuration parameters for the external CPs of the VNF instance.
	 *
	 * @return extCpConfig
	 **/
	@ApiModelProperty(value = "Configuration parameters for the external CPs of the VNF instance. ")

	@Valid

	public List<CpConfiguration> getExtCpConfig() {
		return extCpConfig;
	}

	public void setExtCpConfig(final List<CpConfiguration> extCpConfig) {
		this.extCpConfig = extCpConfig;
	}

	public VnfConfigurationData dhcpServer(final String dhcpServer) {
		this.dhcpServer = dhcpServer;
		return this;
	}

	/**
	 * IP address of the DHCP server that the VNF instance can use to obtain IP
	 * addresses to be assigned to its external CPs.
	 *
	 * @return dhcpServer
	 **/
	@ApiModelProperty(value = "IP address of the DHCP server that the VNF instance can use to obtain IP addresses to be assigned to its external CPs. ")

	public String getDhcpServer() {
		return dhcpServer;
	}

	public void setDhcpServer(final String dhcpServer) {
		this.dhcpServer = dhcpServer;
	}

	public VnfConfigurationData vnfSpecificData(final Map<String, String> vnfSpecificData) {
		this.vnfSpecificData = vnfSpecificData;
		return this;
	}

	/**
	 * Additional configurable properties of the VNF instance declared in the VNFD
	 * as \"VnfConfigurableProperties\".
	 *
	 * @return vnfSpecificData
	 **/
	@ApiModelProperty(value = "Additional configurable properties of the VNF instance declared in the VNFD as \"VnfConfigurableProperties\". ")

	@Valid

	public Map<String, String> getVnfSpecificData() {
		return vnfSpecificData;
	}

	public void setVnfSpecificData(final Map<String, String> vnfSpecificData) {
		this.vnfSpecificData = vnfSpecificData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfConfigurationData vnfConfigurationData = (VnfConfigurationData) o;
		return Objects.equals(this.extCpConfig, vnfConfigurationData.extCpConfig) &&
				Objects.equals(this.dhcpServer, vnfConfigurationData.dhcpServer) &&
				Objects.equals(this.vnfSpecificData, vnfConfigurationData.vnfSpecificData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(extCpConfig, dhcpServer, vnfSpecificData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfConfigurationData {\n");

		sb.append("    extCpConfig: ").append(toIndentedString(extCpConfig)).append("\n");
		sb.append("    dhcpServer: ").append(toIndentedString(dhcpServer)).append("\n");
		sb.append("    vnfSpecificData: ").append(toIndentedString(vnfSpecificData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
