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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information on virtualised compute and storage
 * resources used by a VNFC in a VNF instance.
 */
@ApiModel(description = "This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. ")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class VnfcResourceInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vduId")
	private String vduId = null;

	@JsonProperty("computeResource")
	private ResourceHandle computeResource = null;

	@JsonProperty("storageResourceIds")
	private List<String> storageResourceIds = null;

	@JsonProperty("reservationId")
	private String reservationId = null;

	@JsonProperty("vnfcCpInfo")
	private List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo = null;

	@JsonProperty("metadata")
	private KeyValuePairs metadata = null;

	public VnfcResourceInfo id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VnfcResourceInfo instance.
	 * 
	 * @return id
	 **/
	@JsonProperty("id")
	@ApiModelProperty(required = true, value = "Identifier of this VnfcResourceInfo instance. ")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VnfcResourceInfo vduId(String vduId) {
		this.vduId = vduId;
		return this;
	}

	/**
	 * Reference to the applicable VDU in the VNFD.
	 * 
	 * @return vduId
	 **/
	@JsonProperty("vduId")
	@ApiModelProperty(required = true, value = "Reference to the applicable VDU in the VNFD. ")
	@NotNull
	public String getVduId() {
		return vduId;
	}

	public void setVduId(String vduId) {
		this.vduId = vduId;
	}

	public VnfcResourceInfo computeResource(ResourceHandle computeResource) {
		this.computeResource = computeResource;
		return this;
	}

	/**
	 * Reference to the VirtualCompute resource.
	 * 
	 * @return computeResource
	 **/
	@JsonProperty("computeResource")
	@ApiModelProperty(required = true, value = "Reference to the VirtualCompute resource. ")
	@NotNull
	public ResourceHandle getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(ResourceHandle computeResource) {
		this.computeResource = computeResource;
	}

	public VnfcResourceInfo storageResourceIds(List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
		return this;
	}

	public VnfcResourceInfo addStorageResourceIdsItem(String storageResourceIdsItem) {
		if (this.storageResourceIds == null) {
			this.storageResourceIds = new ArrayList<String>();
		}
		this.storageResourceIds.add(storageResourceIdsItem);
		return this;
	}

	/**
	 * References to the VirtualStorage resources. The value refers to a
	 * VirtualStorageResourceInfo item in the VnfInstance.
	 * 
	 * @return storageResourceIds
	 **/
	@JsonProperty("storageResourceIds")
	@ApiModelProperty(value = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")
	public List<String> getStorageResourceIds() {
		return storageResourceIds;
	}

	public void setStorageResourceIds(List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
	}

	public VnfcResourceInfo reservationId(String reservationId) {
		this.reservationId = reservationId;
		return this;
	}

	/**
	 * The reservation identifier applicable to the resource. It shall be present
	 * when an applicable reservation exists.
	 * 
	 * @return reservationId
	 **/
	@JsonProperty("reservationId")
	@ApiModelProperty(value = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists. ")
	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public VnfcResourceInfo vnfcCpInfo(List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
		return this;
	}

	public VnfcResourceInfo addVnfcCpInfoItem(VnfcResourceInfoVnfcCpInfo vnfcCpInfoItem) {
		if (this.vnfcCpInfo == null) {
			this.vnfcCpInfo = new ArrayList<VnfcResourceInfoVnfcCpInfo>();
		}
		this.vnfcCpInfo.add(vnfcCpInfoItem);
		return this;
	}

	/**
	 * CPs of the VNFC instance. Shall be present when that particular CP of the
	 * VNFC instance is associated to an external CP of the VNF instance. May be
	 * present otherwise.
	 * 
	 * @return vnfcCpInfo
	 **/
	@JsonProperty("vnfcCpInfo")
	@ApiModelProperty(value = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. ")
	public List<VnfcResourceInfoVnfcCpInfo> getVnfcCpInfo() {
		return vnfcCpInfo;
	}

	public void setVnfcCpInfo(List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
	}

	public VnfcResourceInfo metadata(KeyValuePairs metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this resource.
	 * 
	 * @return metadata
	 **/
	@JsonProperty("metadata")
	@ApiModelProperty(value = "Metadata about this resource. ")
	public KeyValuePairs getMetadata() {
		return metadata;
	}

	public void setMetadata(KeyValuePairs metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfcResourceInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
		sb.append("    storageResourceIds: ").append(toIndentedString(storageResourceIds)).append("\n");
		sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
		sb.append("    vnfcCpInfo: ").append(toIndentedString(vnfcCpInfo)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
