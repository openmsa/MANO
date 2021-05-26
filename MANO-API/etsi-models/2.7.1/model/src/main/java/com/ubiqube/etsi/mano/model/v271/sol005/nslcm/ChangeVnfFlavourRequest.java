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
package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.VimConnectionInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Change VNF flavour\&quot; operation. 
 */
@ApiModel(description = "This type represents request parameters for the \"Change VNF flavour\" operation. ")
@Validated


public class ChangeVnfFlavourRequest   {
  @JsonProperty("newFlavourId")
  private String newFlavourId = null;

  @JsonProperty("instantiationLevelId")
  private String instantiationLevelId = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = null;

  @JsonProperty("extManagedVirtualLinks")
  @Valid
  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ChangeVnfFlavourRequest newFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
    return this;
  }

  /**
   * Identifier of the VNF deployment flavour to be instantiated. 
   * @return newFlavourId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavour to be instantiated. ")
  @NotNull


  public String getNewFlavourId() {
    return newFlavourId;
  }

  public void setNewFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
  }

  public ChangeVnfFlavourRequest instantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
    return this;
  }

  /**
   * Identifier of the instantiation level of the deployment flavour to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. 
   * @return instantiationLevelId
  **/
  @ApiModelProperty(value = "Identifier of the instantiation level of the deployment flavour to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated. ")


  public String getInstantiationLevelId() {
    return instantiationLevelId;
  }

  public void setInstantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
  }

  public ChangeVnfFlavourRequest extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourRequest addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. Entries in the list of external VLs that are unchanged need not be supplied as part of this request. 
   * @return extVirtualLinks
  **/
  @ApiModelProperty(value = "Information about external VLs to connect the VNF to. Entries in the list of external VLs that are unchanged need not be supplied as part of this request. ")

  @Valid

  public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeVnfFlavourRequest extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourRequest addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * Information about internal VLs that are managed by the NFVO. 
   * @return extManagedVirtualLinks
  **/
  @ApiModelProperty(value = "Information about internal VLs that are managed by the NFVO. ")

  @Valid

  public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public ChangeVnfFlavourRequest vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public ChangeVnfFlavourRequest addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable. 
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable. ")

  @Valid

  public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public ChangeVnfFlavourRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional input parameters for the instantiation process, specific to the VNF being instantiated, as declared in the VNFD as part of \"InstantiateVnfOpConfig\". 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional input parameters for the instantiation process, specific to the VNF being instantiated, as declared in the VNFD as part of \"InstantiateVnfOpConfig\". ")

  @Valid

  public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeVnfFlavourRequest changeVnfFlavourRequest = (ChangeVnfFlavourRequest) o;
    return Objects.equals(this.newFlavourId, changeVnfFlavourRequest.newFlavourId) &&
        Objects.equals(this.instantiationLevelId, changeVnfFlavourRequest.instantiationLevelId) &&
        Objects.equals(this.extVirtualLinks, changeVnfFlavourRequest.extVirtualLinks) &&
        Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourRequest.extManagedVirtualLinks) &&
        Objects.equals(this.vimConnectionInfo, changeVnfFlavourRequest.vimConnectionInfo) &&
        Objects.equals(this.additionalParams, changeVnfFlavourRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, vimConnectionInfo, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeVnfFlavourRequest {\n");
    
    sb.append("    newFlavourId: ").append(toIndentedString(newFlavourId)).append("\n");
    sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
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

