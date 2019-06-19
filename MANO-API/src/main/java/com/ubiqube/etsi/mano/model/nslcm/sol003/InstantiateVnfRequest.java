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
 * InstantiateVnfRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
public class InstantiateVnfRequest {
	@JsonProperty("flavourId")
	private String flavourId = null;

	@JsonProperty("instantiationLevelId")
	private String instantiationLevelId = null;

	@JsonProperty("extVirtualLinks")
	private List<ExtVirtualLinkData> extVirtualLinks = null;

	@JsonProperty("extManagedVirtualLinks")
	private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

	@JsonProperty("vimConnectionInfo")
	private List<VimConnectionInfo> vimConnectionInfo = null;

	@JsonProperty("localizationLanguage")
	private String localizationLanguage = null;

	@JsonProperty("additionalParams")
	private KeyValuePairs additionalParams = null;

	public InstantiateVnfRequest flavourId(String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	/**
	 * Identifier of the VNF deployment flavour to be instantiated.
	 * 
	 * @return flavourId
	 **/
	@JsonProperty("flavourId")
	@ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavour to be instantiated. ")
	@NotNull
	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(String flavourId) {
		this.flavourId = flavourId;
	}

	public InstantiateVnfRequest instantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	/**
	 * Identifier of the instantiation level of the deployment flavour to be
	 * instantiated. If not present, the default instantiation level as declared in
	 * the VNFD is instantiated.
	 * 
	 * @return instantiationLevelId
	 **/
	@JsonProperty("instantiationLevelId")
	@ApiModelProperty(value = "Identifier of the instantiation level of the deployment flavour to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. ")
	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public InstantiateVnfRequest extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
		return this;
	}

	public InstantiateVnfRequest addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
		if (this.extVirtualLinks == null) {
			this.extVirtualLinks = new ArrayList<ExtVirtualLinkData>();
		}
		this.extVirtualLinks.add(extVirtualLinksItem);
		return this;
	}

	/**
	 * Information about external VLs to connect the VNF to.
	 * 
	 * @return extVirtualLinks
	 **/
	@JsonProperty("extVirtualLinks")
	@ApiModelProperty(value = "Information about external VLs to connect the VNF to. ")
	public List<ExtVirtualLinkData> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public InstantiateVnfRequest extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
		return this;
	}

	public InstantiateVnfRequest addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
		if (this.extManagedVirtualLinks == null) {
			this.extManagedVirtualLinks = new ArrayList<ExtManagedVirtualLinkData>();
		}
		this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
		return this;
	}

	/**
	 * Information about internal VLs that are managed by the NFVO.
	 * 
	 * @return extManagedVirtualLinks
	 **/
	@JsonProperty("extManagedVirtualLinks")
	@ApiModelProperty(value = "Information about internal VLs that are managed by the NFVO. ")
	public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public InstantiateVnfRequest vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
		return this;
	}

	public InstantiateVnfRequest addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
		if (this.vimConnectionInfo == null) {
			this.vimConnectionInfo = new ArrayList<VimConnectionInfo>();
		}
		this.vimConnectionInfo.add(vimConnectionInfoItem);
		return this;
	}

	/**
	 * Information about VIM connections to be used for managing the resources for
	 * the VNF instance, or refer to external / externally-managed virtual links.
	 * This attribute shall only be supported and may be present if VNF-related
	 * resource management in direct mode is applicable.
	 * 
	 * @return vimConnectionInfo
	 **/
	@JsonProperty("vimConnectionInfo")
	@ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable. ")
	public List<VimConnectionInfo> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public InstantiateVnfRequest localizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
		return this;
	}

	/**
	 * Localization language of the VNF to be instantiated. The value shall comply
	 * with the format defined in IETF RFC 5646.
	 * 
	 * @return localizationLanguage
	 **/
	@JsonProperty("localizationLanguage")
	@ApiModelProperty(value = "Localization language of the VNF to be instantiated. The value shall comply with the format defined in IETF RFC 5646. ")
	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	public InstantiateVnfRequest additionalParams(KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional input parameters for the instantiation process, specific to the
	 * VNF being instantiated, as declared in the VNFD as part of
	 * \&quot;InstantiateVnfOpConfig\&quot;.
	 * 
	 * @return additionalParams
	 **/
	@JsonProperty("additionalParams")
	@ApiModelProperty(value = "Additional input parameters for the instantiation process, specific to the VNF being instantiated, as declared in the VNFD as part of  \"InstantiateVnfOpConfig\". ")
	public KeyValuePairs getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(KeyValuePairs additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InstantiateVnfRequest {\n");

		sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
		sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
		sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
		sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
		sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
		sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
