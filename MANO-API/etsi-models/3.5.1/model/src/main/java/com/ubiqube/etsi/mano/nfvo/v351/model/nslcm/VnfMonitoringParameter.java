package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a monitoring parameter that is tracked by the VNFM, for example,  for auto-scaling purposes. It shall comply with the provisions defined in Table 6.5.3.69-1. 
 */
@Schema(description = "This type represents a monitoring parameter that is tracked by the VNFM, for example,  for auto-scaling purposes. It shall comply with the provisions defined in Table 6.5.3.69-1. ")
@Validated


public class VnfMonitoringParameter   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("performanceMetric")
  private String performanceMetric = null;

  public VnfMonitoringParameter id(String id) {
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

  public VnfMonitoringParameter vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfMonitoringParameter name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human readable name of the monitoring parameter, as defined in the VNFD. 
   * @return name
   **/
  @Schema(description = "Human readable name of the monitoring parameter, as defined in the VNFD. ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VnfMonitoringParameter performanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
    return this;
  }

  /**
   * Performance metric that is monitored. This attribute shall contain the related  \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. 
   * @return performanceMetric
   **/
  @Schema(required = true, description = "Performance metric that is monitored. This attribute shall contain the related  \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
      @NotNull

    public String getPerformanceMetric() {
    return performanceMetric;
  }

  public void setPerformanceMetric(String performanceMetric) {
    this.performanceMetric = performanceMetric;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfMonitoringParameter vnfMonitoringParameter = (VnfMonitoringParameter) o;
    return Objects.equals(this.id, vnfMonitoringParameter.id) &&
        Objects.equals(this.vnfdId, vnfMonitoringParameter.vnfdId) &&
        Objects.equals(this.name, vnfMonitoringParameter.name) &&
        Objects.equals(this.performanceMetric, vnfMonitoringParameter.performanceMetric);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfdId, name, performanceMetric);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfMonitoringParameter {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
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
