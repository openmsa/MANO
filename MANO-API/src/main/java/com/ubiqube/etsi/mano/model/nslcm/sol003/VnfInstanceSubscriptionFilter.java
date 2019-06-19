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

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents subscription filter criteria to match VNF instances.
 */
@ApiModel(description = "This type represents subscription filter criteria to match VNF instances. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class VnfInstanceSubscriptionFilter {
	@JsonProperty("vnfdIds")
	private List<String> vnfdIds = null;

	@JsonProperty("vnfProductsFromProviders")
	private List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders = null;

	@JsonProperty("vnfInstanceIds")
	private List<String> vnfInstanceIds = null;

	@JsonProperty("vnfInstanceNames")
	private List<String> vnfInstanceNames = null;

	public VnfInstanceSubscriptionFilter vnfdIds(List<String> vnfdIds) {
		this.vnfdIds = vnfdIds;
		return this;
	}

	public VnfInstanceSubscriptionFilter addVnfdIdsItem(String vnfdIdsItem) {
		if (this.vnfdIds == null) {
			this.vnfdIds = new ArrayList<String>();
		}
		this.vnfdIds.add(vnfdIdsItem);
		return this;
	}

	/**
	 * If present, match VNF instances that were created based on a VNFD identified
	 * by one of the vnfdId values listed in this attribute. The attributes
	 * \&quot;vnfdIds\&quot; and \&quot;vnfProductsFromProviders\&quot; are
	 * alternatives to reference to VNF instances that are based on certain VNFDs in
	 * a filter. They should not be used both in the same filter instance, but one
	 * alternative should be chosen.
	 * 
	 * @return vnfdIds
	 **/
	@JsonProperty("vnfdIds")
	@ApiModelProperty(value = "If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	public List<String> getVnfdIds() {
		return vnfdIds;
	}

	public void setVnfdIds(List<String> vnfdIds) {
		this.vnfdIds = vnfdIds;
	}

	public VnfInstanceSubscriptionFilter vnfProductsFromProviders(List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
		return this;
	}

	public VnfInstanceSubscriptionFilter addVnfProductsFromProvidersItem(VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProductsFromProvidersItem) {
		if (this.vnfProductsFromProviders == null) {
			this.vnfProductsFromProviders = new ArrayList<VnfInstanceSubscriptionFilterVnfProductsFromProviders>();
		}
		this.vnfProductsFromProviders.add(vnfProductsFromProvidersItem);
		return this;
	}

	/**
	 * If present, match VNF instances that belong to VNF products from certain
	 * providers. The attributes \&quot;vnfdIds\&quot; and
	 * \&quot;vnfProductsFromProviders\&quot; are alternatives to reference to VNF
	 * instances that are based on certain VNFDs in a filter. They should not be
	 * used both in the same filter instance, but one alternative should be chosen.
	 * 
	 * @return vnfProductsFromProviders
	 **/
	@JsonProperty("vnfProductsFromProviders")
	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products from certain providers. The attributes \"vnfdIds\" and \"vnfProductsFromProviders\" are alternatives to reference to VNF instances that are based on certain VNFDs in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	public List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> getVnfProductsFromProviders() {
		return vnfProductsFromProviders;
	}

	public void setVnfProductsFromProviders(List<VnfInstanceSubscriptionFilterVnfProductsFromProviders> vnfProductsFromProviders) {
		this.vnfProductsFromProviders = vnfProductsFromProviders;
	}

	public VnfInstanceSubscriptionFilter vnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
		return this;
	}

	public VnfInstanceSubscriptionFilter addVnfInstanceIdsItem(String vnfInstanceIdsItem) {
		if (this.vnfInstanceIds == null) {
			this.vnfInstanceIds = new ArrayList<String>();
		}
		this.vnfInstanceIds.add(vnfInstanceIdsItem);
		return this;
	}

	/**
	 * If present, match VNF instances with an instance identifier listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 * 
	 * @return vnfInstanceIds
	 **/
	@JsonProperty("vnfInstanceIds")
	@ApiModelProperty(value = "If present, match VNF instances with an instance identifier listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	public List<String> getVnfInstanceIds() {
		return vnfInstanceIds;
	}

	public void setVnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
	}

	public VnfInstanceSubscriptionFilter vnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
		return this;
	}

	public VnfInstanceSubscriptionFilter addVnfInstanceNamesItem(String vnfInstanceNamesItem) {
		if (this.vnfInstanceNames == null) {
			this.vnfInstanceNames = new ArrayList<String>();
		}
		this.vnfInstanceNames.add(vnfInstanceNamesItem);
		return this;
	}

	/**
	 * If present, match VNF instances with a VNF Instance Name listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 * 
	 * @return vnfInstanceNames
	 **/
	@JsonProperty("vnfInstanceNames")
	@ApiModelProperty(value = "If present, match VNF instances with a VNF Instance Name listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	public List<String> getVnfInstanceNames() {
		return vnfInstanceNames;
	}

	public void setVnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceSubscriptionFilter {\n");

		sb.append("    vnfdIds: ").append(toIndentedString(vnfdIds)).append("\n");
		sb.append("    vnfProductsFromProviders: ").append(toIndentedString(vnfProductsFromProviders)).append("\n");
		sb.append("    vnfInstanceIds: ").append(toIndentedString(vnfInstanceIds)).append("\n");
		sb.append("    vnfInstanceNames: ").append(toIndentedString(vnfInstanceNames)).append("\n");
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
