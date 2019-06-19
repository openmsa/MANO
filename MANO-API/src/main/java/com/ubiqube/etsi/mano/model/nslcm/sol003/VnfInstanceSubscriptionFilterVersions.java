/*
 * SOL003 - VNF Lifecycle Management interface
 * SOL003 - VNF Lifecycle Management interface definition  IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to. In case of discrepancies the published ETSI Group Specification takes precedence.  In clause 4.3.2 of ETSI GS NFV-SOL 003 v2.4.1, an attribute-based filtering mechanism is defined. This mechanism is currently not included in the corresponding OpenAPI design for this GS version. Changes to the attribute-based filtering mechanism are being considered in v2.5.1 of this GS for inclusion in the corresponding future ETSI NFV OpenAPI design. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis&list_id=61&product=NFV&resolution=
 *
 * OpenAPI spec version: 1.1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ubiqube.etsi.mano.model.nslcm.sol003;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VnfInstanceSubscriptionFilterVersions
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class VnfInstanceSubscriptionFilterVersions {
	@JsonProperty("vnfSoftwareVersion")
	private String vnfSoftwareVersion = null;

	@JsonProperty("vnfdVersions")
	private List<String> vnfdVersions = null;

	public VnfInstanceSubscriptionFilterVersions vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Software version to match.
	 * 
	 * @return vnfSoftwareVersion
	 **/
	@JsonProperty("vnfSoftwareVersion")
	@ApiModelProperty(required = true, value = "Software version to match. ")
	@NotNull
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfInstanceSubscriptionFilterVersions vnfdVersions(List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
		return this;
	}

	public VnfInstanceSubscriptionFilterVersions addVnfdVersionsItem(String vnfdVersionsItem) {
		if (this.vnfdVersions == null) {
			this.vnfdVersions = new ArrayList<String>();
		}
		this.vnfdVersions.add(vnfdVersionsItem);
		return this;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain VNFD
	 * versions, a certain software version and a certain product name, from one
	 * particular provider.
	 * 
	 * @return vnfdVersions
	 **/
	@JsonProperty("vnfdVersions")
	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")
	public List<String> getVnfdVersions() {
		return vnfdVersions;
	}

	public void setVnfdVersions(List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceSubscriptionFilterVersions {\n");

		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
