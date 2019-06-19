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

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Cancellation mode. GRACEFUL: If the VNF LCM operation occurrence is in
 * \&quot;PROCESSING\&quot; or \&quot;ROLLING_BACK\&quot; state, the VNFM shall
 * not start any new resource management operation and shall wait for the
 * ongoing resource management operations in the underlying system, typically
 * the VIM, to finish execution or to time out. After that, the VNFM shall put
 * the operation occurrence into the FAILED_TEMP state. If the VNF LCM operation
 * occurrence is in \&quot;STARTING\&quot; state, the VNFM shall not start any
 * resource management operation and shall wait for the granting request to
 * finish execution or time out. After that, the VNFM shall put the operation
 * occurrence into the ROLLED_BACK state. FORCEFUL: If the VNF LCM operation
 * occurrence is in \&quot;PROCESSING\&quot; or \&quot;ROLLING_BACK\&quot;
 * state, the VNFM shall not start any new resource management operation, shall
 * cancel the ongoing resource management operations in the underlying system,
 * typically the VIM, and shall wait for the cancellation to finish or to time
 * out. After that, the VNFM shall put the operation occurrence into the
 * FAILED_TEMP state. If the VNF LCM operation occurrence is in
 * \&quot;STARTING\&quot; state, the VNFM shall not start any resource
 * management operation and put the operation occurrence into the ROLLED_BACK
 * state.
 */
public enum CancelModeType {

	GRACEFUL("GRACEFUL"),

	FORCEFUL("FORCEFUL");

	private String value;

	CancelModeType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CancelModeType fromValue(String text) {
		for (final CancelModeType b : CancelModeType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
