package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * STARTED - The VNF instance is up and running. STOPPED - The VNF instance has been shut down. 
 */
public enum OperationalStates {
  STARTED("STARTED"),
    STOPPED("STOPPED");

  private String value;

  OperationalStates(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OperationalStates fromValue(String text) {
    for (OperationalStates b : OperationalStates.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
