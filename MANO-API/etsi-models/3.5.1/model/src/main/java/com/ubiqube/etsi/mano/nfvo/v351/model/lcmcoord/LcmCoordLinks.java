package com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class LcmCoordLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("nsLcmOpOcc")
  private Link nsLcmOpOcc = null;

  @JsonProperty("nsInstance")
  private Link nsInstance = null;

  public LcmCoordLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public LcmCoordLinks nsLcmOpOcc(Link nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
    return this;
  }

  /**
   * Get nsLcmOpOcc
   * @return nsLcmOpOcc
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getNsLcmOpOcc() {
    return nsLcmOpOcc;
  }

  public void setNsLcmOpOcc(Link nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
  }

  public LcmCoordLinks nsInstance(Link nsInstance) {
    this.nsInstance = nsInstance;
    return this;
  }

  /**
   * Get nsInstance
   * @return nsInstance
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getNsInstance() {
    return nsInstance;
  }

  public void setNsInstance(Link nsInstance) {
    this.nsInstance = nsInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LcmCoordLinks lcmCoordLinks = (LcmCoordLinks) o;
    return Objects.equals(this.self, lcmCoordLinks.self) &&
        Objects.equals(this.nsLcmOpOcc, lcmCoordLinks.nsLcmOpOcc) &&
        Objects.equals(this.nsInstance, lcmCoordLinks.nsInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, nsLcmOpOcc, nsInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoordLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nsLcmOpOcc: ").append(toIndentedString(nsLcmOpOcc)).append("\n");
    sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
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
