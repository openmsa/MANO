package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF instance. It shall comply with the provisions defined in table 6.5.3.57-1. NOTE: Clause B.3.2 of ETSI GS NFV-SOL 003 provides examples illustrating the relationship among the        different run-time information elements (CP, VL and link ports) used to represent the connectivity        of a VNF.  NOTE 1: Modifying the value of this attribute shall not be performed when conflicts exist between the          previous and the newly referred VNF package, i.e. when the new VNFD is changed with respect to          the previous VNFD in other aspects than merely referencing to other VNF software images.          In order to avoid misalignment of the VnfInstance with the current VNF&#x27;s on-boarded VNF Package,          the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall          be kept in sync with the values in the VNFD. NOTE 2: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. NOTE 3: VNF configurable properties are sometimes also referred to as configuration parameters applicable          to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated,          some are set prior to instantiation (are part of initial configuration) and can be modified later,          and others can be set only after instantiation. The applicability of certain configuration may depend          on the VNF and the required operation of the VNF at a certain point in time. NOTE 4: It is possible to have several ExtManagedVirtualLinkInfo for the same VNF internal VL in case of a          multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkInfo corresponding to the same          VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed          multi-site VL instance (refer to clause 6.5.3.59). NOTE 5: Even though externally-managed internal VLs are also used for VNF-internal connectivity, they shall not          be listed in the \&quot;vnfVirtualLinkResourceInfo\&quot; attribute as this would be redundant. 
 */
@Schema(description = "This type represents a VNF instance. It shall comply with the provisions defined in table 6.5.3.57-1. NOTE: Clause B.3.2 of ETSI GS NFV-SOL 003 provides examples illustrating the relationship among the        different run-time information elements (CP, VL and link ports) used to represent the connectivity        of a VNF.  NOTE 1: Modifying the value of this attribute shall not be performed when conflicts exist between the          previous and the newly referred VNF package, i.e. when the new VNFD is changed with respect to          the previous VNFD in other aspects than merely referencing to other VNF software images.          In order to avoid misalignment of the VnfInstance with the current VNF's on-boarded VNF Package,          the values of attributes in the VnfInstance that have corresponding attributes in the VNFD shall          be kept in sync with the values in the VNFD. NOTE 2: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. NOTE 3: VNF configurable properties are sometimes also referred to as configuration parameters applicable          to a VNF. Some of these are set prior to instantiation and cannot be modified if the VNF is instantiated,          some are set prior to instantiation (are part of initial configuration) and can be modified later,          and others can be set only after instantiation. The applicability of certain configuration may depend          on the VNF and the required operation of the VNF at a certain point in time. NOTE 4: It is possible to have several ExtManagedVirtualLinkInfo for the same VNF internal VL in case of a          multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkInfo corresponding to the same          VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed          multi-site VL instance (refer to clause 6.5.3.59). NOTE 5: Even though externally-managed internal VLs are also used for VNF-internal connectivity, they shall not          be listed in the \"vnfVirtualLinkResourceInfo\" attribute as this would be redundant. ")
@Validated


public class VnfInstance   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProductName")
  private String vnfProductName = null;

  @JsonProperty("vnfSoftwareVersion")
  private String vnfSoftwareVersion = null;

  @JsonProperty("vnfdVersion")
  private String vnfdVersion = null;

  @JsonProperty("vnfPkgId")
  private String vnfPkgId = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("vimId")
  private String vimId = null;

  /**
   * The instantiation state of the VNF. Permitted values: - NOT_INSTANTIATED: The VNF instance is terminated or not instantiated. - INSTANTIATED: The VNF instance is instantiated. 
   */
  public enum InstantiationStateEnum {
    NOT_INSTANTIATED("NOT_INSTANTIATED"),
    
    INSTANTIATED("INSTANTIATED");

    private String value;

    InstantiationStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InstantiationStateEnum fromValue(String text) {
      for (InstantiationStateEnum b : InstantiationStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("instantiationState")
  private InstantiationStateEnum instantiationState = null;

  @JsonProperty("instantiatedVnfInfo")
  private VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  public VnfInstance id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfInstance vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Name of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return vnfInstanceName
   **/
  @Schema(description = "Name of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. ")
  
    public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public VnfInstance vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. 
   * @return vnfInstanceDescription
   **/
  @Schema(description = "Human-readable description of the VNF instance. Modifications to this attribute can be requested using the \"ModifyVnfInfoData\" structure. ")
  
    public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public VnfInstance vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfInstance vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Provider of the VNF and the VNFD. The value is copied from the VNFD. 
   * @return vnfProvider
   **/
  @Schema(required = true, description = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
      @NotNull

    public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfInstance vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Name to identify the VNF Product. The value is copied from the VNFD. 
   * @return vnfProductName
   **/
  @Schema(required = true, description = "Name to identify the VNF Product. The value is copied from the VNFD. ")
      @NotNull

    public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfInstance vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Get vnfSoftwareVersion
   * @return vnfSoftwareVersion
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfInstance vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

  /**
   * Get vnfdVersion
   * @return vnfdVersion
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public VnfInstance vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  /**
   * Get vnfPkgId
   * @return vnfPkgId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfInstance vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Get vnfConfigurableProperties
   * @return vnfConfigurableProperties
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInstance vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
   **/
  @Schema(description = "")
  
    public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public VnfInstance instantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
    return this;
  }

  /**
   * The instantiation state of the VNF. Permitted values: - NOT_INSTANTIATED: The VNF instance is terminated or not instantiated. - INSTANTIATED: The VNF instance is instantiated. 
   * @return instantiationState
   **/
  @Schema(required = true, description = "The instantiation state of the VNF. Permitted values: - NOT_INSTANTIATED: The VNF instance is terminated or not instantiated. - INSTANTIATED: The VNF instance is instantiated. ")
      @NotNull

    public InstantiationStateEnum getInstantiationState() {
    return instantiationState;
  }

  public void setInstantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
  }

  public VnfInstance instantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
    return this;
  }

  /**
   * Get instantiatedVnfInfo
   * @return instantiatedVnfInfo
   **/
  @Schema(description = "")
  
    @Valid
    public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
    return instantiatedVnfInfo;
  }

  public void setInstantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
  }

  public VnfInstance metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfInstance extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Get extensions
   * @return extensions
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstance vnfInstance = (VnfInstance) o;
    return Objects.equals(this.id, vnfInstance.id) &&
        Objects.equals(this.vnfInstanceName, vnfInstance.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, vnfInstance.vnfInstanceDescription) &&
        Objects.equals(this.vnfdId, vnfInstance.vnfdId) &&
        Objects.equals(this.vnfProvider, vnfInstance.vnfProvider) &&
        Objects.equals(this.vnfProductName, vnfInstance.vnfProductName) &&
        Objects.equals(this.vnfSoftwareVersion, vnfInstance.vnfSoftwareVersion) &&
        Objects.equals(this.vnfdVersion, vnfInstance.vnfdVersion) &&
        Objects.equals(this.vnfPkgId, vnfInstance.vnfPkgId) &&
        Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
        Objects.equals(this.vimId, vnfInstance.vimId) &&
        Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
        Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
        Objects.equals(this.metadata, vnfInstance.metadata) &&
        Objects.equals(this.extensions, vnfInstance.extensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfPkgId, vnfConfigurableProperties, vimId, instantiationState, instantiatedVnfInfo, metadata, extensions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstance {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
    sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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
