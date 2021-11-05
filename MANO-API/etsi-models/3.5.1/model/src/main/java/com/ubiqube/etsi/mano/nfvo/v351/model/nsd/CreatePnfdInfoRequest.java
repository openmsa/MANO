package com.ubiqube.etsi.mano.nfvo.v351.model.nsd;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * User-defined data for the PNF descriptor resource to be created. It shall be
 * present when the user defined data is set for the individual PNF descriptor
 * resource to be created.
 */
@Schema(description = "User-defined data for the PNF descriptor resource to be created. It shall be present when the user defined data is set for the individual PNF descriptor resource to be created. ")
@Validated

public class CreatePnfdInfoRequest {
	@JsonProperty("userDefinedData")
	private Map<String, Object> userDefinedData = null;

	public CreatePnfdInfoRequest userDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 *
	 * @return userDefinedData
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, Object> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, Object> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CreatePnfdInfoRequest createPnfdInfoRequest = (CreatePnfdInfoRequest) o;
		return Objects.equals(this.userDefinedData, createPnfdInfoRequest.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreatePnfdInfoRequest {\n");

		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
