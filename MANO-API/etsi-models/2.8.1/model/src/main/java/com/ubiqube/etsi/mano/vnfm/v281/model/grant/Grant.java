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
package com.ubiqube.etsi.mano.vnfm.v281.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.GrantInfo;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.GrantLinks;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.GrantVimAssets;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.VimConnectionInfo;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.ZoneGroupInfo;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.ZoneInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a grant. 
 */
@ApiModel(description = "This type represents a grant. ")
@Validated

public class Grant   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfLcmOpOccId")
  private String vnfLcmOpOccId = null;

  @JsonProperty("vimConnections")
  @Valid
  private List<VimConnectionInfo> vimConnections = null;

  @JsonProperty("zones")
  @Valid
  private List<ZoneInfo> zones = null;

  @JsonProperty("zoneGroups")
  @Valid
  private List<ZoneGroupInfo> zoneGroups = null;

  @JsonProperty("computeReservationId")
  private String computeReservationId = null;

  @JsonProperty("networkReservationId")
  private String networkReservationId = null;

  @JsonProperty("storageReservationId")
  private String storageReservationId = null;

  @JsonProperty("addResources")
  @Valid
  private List<GrantInfo> addResources = null;

  @JsonProperty("tempResources")
  @Valid
  private List<GrantInfo> tempResources = null;

  @JsonProperty("removeResources")
  @Valid
  private List<GrantInfo> removeResources = null;

  @JsonProperty("updateResources")
  @Valid
  private List<GrantInfo> updateResources = null;

  @JsonProperty("vimAssets")
  private GrantVimAssets vimAssets = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = null;

  @JsonProperty("extManagedVirtualLinks")
  @Valid
  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("_links")
  private GrantLinks links = null;

  public Grant id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the grant. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the grant. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Grant vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Identifier of the related VNF instance. See note 6. NOTE 6: The NFVO shall set the value of the attribute by copying the value  from the associated GrantRequest. 
   * @return vnfInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the related VNF instance. See note 6. NOTE 6: The NFVO shall set the value of the attribute by copying the value  from the associated GrantRequest. ")
  @NotNull


  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public Grant vnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
    return this;
  }

  /**
   * Identifier of the related VNF lifecycle management operation occurrence. See note 6. NOTE 6: The NFVO shall set the value of the attribute by copying the value  from the associated GrantRequest. 
   * @return vnfLcmOpOccId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the related VNF lifecycle management operation occurrence. See note 6. NOTE 6: The NFVO shall set the value of the attribute by copying the value  from the associated GrantRequest. ")
  @NotNull


  public String getVnfLcmOpOccId() {
    return vnfLcmOpOccId;
  }

  public void setVnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
  }

  public Grant vimConnections(List<VimConnectionInfo> vimConnections) {
    this.vimConnections = vimConnections;
    return this;
  }

  public Grant addVimConnectionsItem(VimConnectionInfo vimConnectionsItem) {
    if (this.vimConnections == null) {
      this.vimConnections = new ArrayList<>();
    }
    this.vimConnections.add(vimConnectionsItem);
    return this;
  }

  /**
   * Provides information regarding VIM connections that are approved to be used by the VNFM to allocate resources, and provides parameters of these VIM connections. The VNFM shall update the \" vimConnectionInfo\" attribute of the \"VnfInstance\" structure by adding unknown entries received in this attribute. This attribute is not intended for the modification of VimConnection entries passed earlier; for that, the VnfInfoModificationRequest structure shall be used. This attribute shall only be supported when VNF-related Resource Management in direct mode is applicable. In direct mode, this parameter shall be absent if the VIM information was configured to the VNFM in another way, present otherwise. See note 1. NOTE 1: This interface allows to signal the use of multiple VIMs per VNF. However, due to the partial support of this feature in the present release, it is recommended in the present document that the number of entries in the \"vims\" attribute in the Grant is not greater than 1. 
   * @return vimConnections
  **/
  @ApiModelProperty(value = "Provides information regarding VIM connections that are approved to be used by the VNFM to allocate resources, and provides parameters of these VIM connections. The VNFM shall update the \" vimConnectionInfo\" attribute of the \"VnfInstance\" structure by adding unknown entries received in this attribute. This attribute is not intended for the modification of VimConnection entries passed earlier; for that, the VnfInfoModificationRequest structure shall be used. This attribute shall only be supported when VNF-related Resource Management in direct mode is applicable. In direct mode, this parameter shall be absent if the VIM information was configured to the VNFM in another way, present otherwise. See note 1. NOTE 1: This interface allows to signal the use of multiple VIMs per VNF. However, due to the partial support of this feature in the present release, it is recommended in the present document that the number of entries in the \"vims\" attribute in the Grant is not greater than 1. ")

  @Valid

  public List<VimConnectionInfo> getVimConnections() {
    return vimConnections;
  }

  public void setVimConnections(List<VimConnectionInfo> vimConnections) {
    this.vimConnections = vimConnections;
  }

  public Grant zones(List<ZoneInfo> zones) {
    this.zones = zones;
    return this;
  }

  public Grant addZonesItem(ZoneInfo zonesItem) {
    if (this.zones == null) {
      this.zones = new ArrayList<>();
    }
    this.zones.add(zonesItem);
    return this;
  }

  /**
   * Identifies resource zones where the resources are approved to be allocated by the VNFM. 
   * @return zones
  **/
  @ApiModelProperty(value = "Identifies resource zones where the resources are approved to be allocated by the VNFM. ")

  @Valid

  public List<ZoneInfo> getZones() {
    return zones;
  }

  public void setZones(List<ZoneInfo> zones) {
    this.zones = zones;
  }

  public Grant zoneGroups(List<ZoneGroupInfo> zoneGroups) {
    this.zoneGroups = zoneGroups;
    return this;
  }

  public Grant addZoneGroupsItem(ZoneGroupInfo zoneGroupsItem) {
    if (this.zoneGroups == null) {
      this.zoneGroups = new ArrayList<>();
    }
    this.zoneGroups.add(zoneGroupsItem);
    return this;
  }

  /**
   * Information about groups of resource zones that are related and that the NFVO has chosen to fulfil a zoneGroup constraint in the GrantVnfLifecycleOperation request. This information confirms that the NFVO has honoured the zoneGroup constraints that were passed as part of \"placementConstraints\" in the GrantRequest. 
   * @return zoneGroups
  **/
  @ApiModelProperty(value = "Information about groups of resource zones that are related and that the NFVO has chosen to fulfil a zoneGroup constraint in the GrantVnfLifecycleOperation request. This information confirms that the NFVO has honoured the zoneGroup constraints that were passed as part of \"placementConstraints\" in the GrantRequest. ")

  @Valid

  public List<ZoneGroupInfo> getZoneGroups() {
    return zoneGroups;
  }

  public void setZoneGroups(List<ZoneGroupInfo> zoneGroups) {
    this.zoneGroups = zoneGroups;
  }

  public Grant computeReservationId(String computeReservationId) {
    this.computeReservationId = computeReservationId;
    return this;
  }

  /**
   * Information that identifies a reservation applicable to the compute resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. 
   * @return computeReservationId
  **/
  @ApiModelProperty(value = "Information that identifies a reservation applicable to the compute resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. ")


  public String getComputeReservationId() {
    return computeReservationId;
  }

  public void setComputeReservationId(String computeReservationId) {
    this.computeReservationId = computeReservationId;
  }

  public Grant networkReservationId(String networkReservationId) {
    this.networkReservationId = networkReservationId;
    return this;
  }

  /**
   * Information that identifies a reservation applicable to the network resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. 
   * @return networkReservationId
  **/
  @ApiModelProperty(value = "Information that identifies a reservation applicable to the network resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. ")


  public String getNetworkReservationId() {
    return networkReservationId;
  }

  public void setNetworkReservationId(String networkReservationId) {
    this.networkReservationId = networkReservationId;
  }

  public Grant storageReservationId(String storageReservationId) {
    this.storageReservationId = storageReservationId;
    return this;
  }

  /**
   * Information that identifies a reservation applicable to the storage resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. 
   * @return storageReservationId
  **/
  @ApiModelProperty(value = "Information that identifies a reservation applicable to the storage resource requirements of the corresponding grant request. See note 2. NOTE 2: At least one of (computeReservationId, networkReservationId, storageReservationId) shall be present when policy is GRANT_RESERVE_SINGLE and an applicable reservation exists. None of these shall be present otherwise. ")


  public String getStorageReservationId() {
    return storageReservationId;
  }

  public void setStorageReservationId(String storageReservationId) {
    this.storageReservationId = storageReservationId;
  }

  public Grant addResources(List<GrantInfo> addResources) {
    this.addResources = addResources;
    return this;
  }

  public Grant addAddResourcesItem(GrantInfo addResourcesItem) {
    if (this.addResources == null) {
      this.addResources = new ArrayList<>();
    }
    this.addResources.add(addResourcesItem);
    return this;
  }

  /**
   * List of resources that are approved to be added, with one entry per resource. Shall be set when resources are approved to be added. 
   * @return addResources
  **/
  @ApiModelProperty(value = "List of resources that are approved to be added, with one entry per resource. Shall be set when resources are approved to be added. ")

  @Valid

  public List<GrantInfo> getAddResources() {
    return addResources;
  }

  public void setAddResources(List<GrantInfo> addResources) {
    this.addResources = addResources;
  }

  public Grant tempResources(List<GrantInfo> tempResources) {
    this.tempResources = tempResources;
    return this;
  }

  public Grant addTempResourcesItem(GrantInfo tempResourcesItem) {
    if (this.tempResources == null) {
      this.tempResources = new ArrayList<>();
    }
    this.tempResources.add(tempResourcesItem);
    return this;
  }

  /**
   * List of resources that are approved to be temporarily instantiated during the runtime of the lifecycle operation, with one entry per resource. Shall be set when resources are approved to be temporarily  instantiated. 
   * @return tempResources
  **/
  @ApiModelProperty(value = "List of resources that are approved to be temporarily instantiated during the runtime of the lifecycle operation, with one entry per resource. Shall be set when resources are approved to be temporarily  instantiated. ")

  @Valid

  public List<GrantInfo> getTempResources() {
    return tempResources;
  }

  public void setTempResources(List<GrantInfo> tempResources) {
    this.tempResources = tempResources;
  }

  public Grant removeResources(List<GrantInfo> removeResources) {
    this.removeResources = removeResources;
    return this;
  }

  public Grant addRemoveResourcesItem(GrantInfo removeResourcesItem) {
    if (this.removeResources == null) {
      this.removeResources = new ArrayList<>();
    }
    this.removeResources.add(removeResourcesItem);
    return this;
  }

  /**
   * List of resources that are approved to be removed, with one entry per resource. Shall be set when resources are approved to be removed. 
   * @return removeResources
  **/
  @ApiModelProperty(value = "List of resources that are approved to be removed, with one entry per resource. Shall be set when resources are approved to be removed. ")

  @Valid

  public List<GrantInfo> getRemoveResources() {
    return removeResources;
  }

  public void setRemoveResources(List<GrantInfo> removeResources) {
    this.removeResources = removeResources;
  }

  public Grant updateResources(List<GrantInfo> updateResources) {
    this.updateResources = updateResources;
    return this;
  }

  public Grant addUpdateResourcesItem(GrantInfo updateResourcesItem) {
    if (this.updateResources == null) {
      this.updateResources = new ArrayList<>();
    }
    this.updateResources.add(updateResourcesItem);
    return this;
  }

  /**
   * List of resources that are approved to be modified, with one entry per resource. Shall be set when resources are approved to be updated. 
   * @return updateResources
  **/
  @ApiModelProperty(value = "List of resources that are approved to be modified, with one entry per resource. Shall be set when resources are approved to be updated. ")

  @Valid

  public List<GrantInfo> getUpdateResources() {
    return updateResources;
  }

  public void setUpdateResources(List<GrantInfo> updateResources) {
    this.updateResources = updateResources;
  }

  public Grant vimAssets(GrantVimAssets vimAssets) {
    this.vimAssets = vimAssets;
    return this;
  }

  /**
   * Get vimAssets
   * @return vimAssets
  **/
  @ApiModelProperty(value = "")

  @Valid

  public GrantVimAssets getVimAssets() {
    return vimAssets;
  }

  public void setVimAssets(GrantVimAssets vimAssets) {
    this.vimAssets = vimAssets;
  }

  public Grant extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public Grant addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. See note 5 and note 7. If this attribute is present according to note 5 or note 7, it need not  contain those entries that are unchanged compared to the entries that  were passed in the LCM operation which is related to this granting exchange. NOTE 5: External and/or externally-managed internal VLs can be passed in VNF  lifecycle management operation requests such as InstantiateVnf, ChangeVnfFlavour  or ChangeExtVnfConnectivity and/or in the grant response. The NFVO may choose to  override in the grant response external and/or externally-managed VL instances  that have been passed previously in the associated VNF lifecycle management request,  if the lifecycle management request has originated from the NFVO itself. NOTE 7: In case of granting an InstantiateVnf request that has originated from the  NFVO and that did not contain the \"extVirtualLinks\" attribute, this attribute shall  be set by the NFVO. Further in case of granting an InstantiateVnf request that has  originated from the NFVO and that did not contain the \"extManagedVirtualLinks\" attribute,  this attribute shall be set by the NFVO if there is the need to provide information about  externally-managed virtual links. 
   * @return extVirtualLinks
  **/
  @ApiModelProperty(value = "Information about external VLs to connect the VNF to. See note 5 and note 7. If this attribute is present according to note 5 or note 7, it need not  contain those entries that are unchanged compared to the entries that  were passed in the LCM operation which is related to this granting exchange. NOTE 5: External and/or externally-managed internal VLs can be passed in VNF  lifecycle management operation requests such as InstantiateVnf, ChangeVnfFlavour  or ChangeExtVnfConnectivity and/or in the grant response. The NFVO may choose to  override in the grant response external and/or externally-managed VL instances  that have been passed previously in the associated VNF lifecycle management request,  if the lifecycle management request has originated from the NFVO itself. NOTE 7: In case of granting an InstantiateVnf request that has originated from the  NFVO and that did not contain the \"extVirtualLinks\" attribute, this attribute shall  be set by the NFVO. Further in case of granting an InstantiateVnf request that has  originated from the NFVO and that did not contain the \"extManagedVirtualLinks\" attribute,  this attribute shall be set by the NFVO if there is the need to provide information about  externally-managed virtual links. ")

  @Valid

  public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public Grant extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public Grant addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * Information about internal VLs that are managed by other entities than the VNFM. See note 4, note 5 and note 57 NOTE 4: The indication of externally-managed internal VLs is needed  in case networks have been pre-configured for use with certain VNFs,  for instance to ensure that these networks have certain properties such as security or acceleration features, or to address particular network topologies. The present document assumes that externally-managed  internal VLs are managed by the NFVO and created towards the VIM). NOTE 5: External and/or externally-managed internal VLs can be passed in VNF  lifecycle management operation requests such as InstantiateVnf, ChangeVnfFlavour  or ChangeExtVnfConnectivity and/or in the grant response. The NFVO may choose to  override in the grant response external and/or externally-managed VL instances  that have been passed previously in the associated VNF lifecycle management request,  if the lifecycle management request has originated from the NFVO itself. NOTE 7: In case of granting an InstantiateVnf request that has originated from the  NFVO and that did not contain the \"extVirtualLinks\" attribute, this attribute shall  be set by the NFVO. Further in case of granting an InstantiateVnf request that has  originated from the NFVO and that did not contain the \"extManagedVirtualLinks\" attribute,  this attribute shall be set by the NFVO if there is the need to provide information about  externally-managed virtual links. 
   * @return extManagedVirtualLinks
  **/
  @ApiModelProperty(value = "Information about internal VLs that are managed by other entities than the VNFM. See note 4, note 5 and note 57 NOTE 4: The indication of externally-managed internal VLs is needed  in case networks have been pre-configured for use with certain VNFs,  for instance to ensure that these networks have certain properties such as security or acceleration features, or to address particular network topologies. The present document assumes that externally-managed  internal VLs are managed by the NFVO and created towards the VIM). NOTE 5: External and/or externally-managed internal VLs can be passed in VNF  lifecycle management operation requests such as InstantiateVnf, ChangeVnfFlavour  or ChangeExtVnfConnectivity and/or in the grant response. The NFVO may choose to  override in the grant response external and/or externally-managed VL instances  that have been passed previously in the associated VNF lifecycle management request,  if the lifecycle management request has originated from the NFVO itself. NOTE 7: In case of granting an InstantiateVnf request that has originated from the  NFVO and that did not contain the \"extVirtualLinks\" attribute, this attribute shall  be set by the NFVO. Further in case of granting an InstantiateVnf request that has  originated from the NFVO and that did not contain the \"extManagedVirtualLinks\" attribute,  this attribute shall be set by the NFVO if there is the need to provide information about  externally-managed virtual links. ")

  @Valid

  public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public Grant additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional parameters passed by the NFVO, specific to the VNF and the LCM operation. 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional parameters passed by the NFVO, specific to the VNF and the LCM operation. ")

  @Valid

  public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public Grant links(GrantLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public GrantLinks getLinks() {
    return links;
  }

  public void setLinks(GrantLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grant grant = (Grant) o;
    return Objects.equals(this.id, grant.id) &&
        Objects.equals(this.vnfInstanceId, grant.vnfInstanceId) &&
        Objects.equals(this.vnfLcmOpOccId, grant.vnfLcmOpOccId) &&
        Objects.equals(this.vimConnections, grant.vimConnections) &&
        Objects.equals(this.zones, grant.zones) &&
        Objects.equals(this.zoneGroups, grant.zoneGroups) &&
        Objects.equals(this.computeReservationId, grant.computeReservationId) &&
        Objects.equals(this.networkReservationId, grant.networkReservationId) &&
        Objects.equals(this.storageReservationId, grant.storageReservationId) &&
        Objects.equals(this.addResources, grant.addResources) &&
        Objects.equals(this.tempResources, grant.tempResources) &&
        Objects.equals(this.removeResources, grant.removeResources) &&
        Objects.equals(this.updateResources, grant.updateResources) &&
        Objects.equals(this.vimAssets, grant.vimAssets) &&
        Objects.equals(this.extVirtualLinks, grant.extVirtualLinks) &&
        Objects.equals(this.extManagedVirtualLinks, grant.extManagedVirtualLinks) &&
        Objects.equals(this.additionalParams, grant.additionalParams) &&
        Objects.equals(this.links, grant.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfInstanceId, vnfLcmOpOccId, vimConnections, zones, zoneGroups, computeReservationId, networkReservationId, storageReservationId, addResources, tempResources, removeResources, updateResources, vimAssets, extVirtualLinks, extManagedVirtualLinks, additionalParams, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Grant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
    sb.append("    vimConnections: ").append(toIndentedString(vimConnections)).append("\n");
    sb.append("    zones: ").append(toIndentedString(zones)).append("\n");
    sb.append("    zoneGroups: ").append(toIndentedString(zoneGroups)).append("\n");
    sb.append("    computeReservationId: ").append(toIndentedString(computeReservationId)).append("\n");
    sb.append("    networkReservationId: ").append(toIndentedString(networkReservationId)).append("\n");
    sb.append("    storageReservationId: ").append(toIndentedString(storageReservationId)).append("\n");
    sb.append("    addResources: ").append(toIndentedString(addResources)).append("\n");
    sb.append("    tempResources: ").append(toIndentedString(tempResources)).append("\n");
    sb.append("    removeResources: ").append(toIndentedString(removeResources)).append("\n");
    sb.append("    updateResources: ").append(toIndentedString(updateResources)).append("\n");
    sb.append("    vimAssets: ").append(toIndentedString(vimAssets)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

