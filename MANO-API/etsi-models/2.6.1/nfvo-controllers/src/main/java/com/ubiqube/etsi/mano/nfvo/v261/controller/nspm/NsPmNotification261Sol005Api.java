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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nspm;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PerformanceInformationAvailableNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdCrossedNotification;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * SOL005 - NS Performance Management Interface
 *
 * <p>
 * SOL005 - NS Performance Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
@RequestMapping("/sol005/nspm/v1/")
@RolesAllowed({ "ROLE_OSSBSS" })
public interface NsPmNotification261Sol005Api {

	/**
	 * Notify about PM related events
	 *
	 * The POST method delivers a notification regarding a performance management event from the server to the client. This method shall follow the provisions specified in the Tables 7.4.9.3.1-1 and 7.4.9.3.1-2 for URI query parameters,
	 *
	 */
	@ApiOperation(value = "Notify about PM related events", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The notification was delivered successfully. "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized. If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-PerformanceInformationAvailableNotification", consumes = { "application/json" }, produces = { "application/json" })
	void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPerformanceInformationAvailableNotificationPost(@Valid PerformanceInformationAvailableNotification performanceInformationAvailableNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Test the notification endpoint
	 *
	 * The GET method allows the server to test the notification endpoint that is provided by the client, e.g. during subscription. This method shall follow the provisions specified in the Tables 7.4.9.3.2-1 and 7.4.9.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@ApiOperation(value = "Test the notification endpoint", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content           The notification endpoint was tested successfully. The response body shall be empty.  "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized. If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-ThresholdCrossedNotification", consumes = { "application/json" }, produces = { "application/json" })
	void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationGet(@RequestHeader("Accept") String accept);

	/**
	 * Notify about PM related events
	 *
	 * The POST method delivers a notification regarding a performance management event from the server to the client. This method shall follow the provisions specified in the Tables 7.4.9.3.1-1 and 7.4.9.3.1-2 for URI query parameters,
	 *
	 */
	@ApiOperation(value = "Notify about PM related events", tags = {})
	@ApiResponses(value = { @ApiResponse(code = 204, message = "204 No Content The notification was delivered successfully.  "), @ApiResponse(code = 400, message = "Bad Request. Error: Invalid attribute-based filtering parameters. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error.        ", response = ProblemDetails.class), @ApiResponse(code = 401, message = "Unauthorized. If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class), @ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond withthis response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-ThresholdCrossedNotification", consumes = { "application/json" }, produces = { "application/json" })
	void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationPost(@Valid ThresholdCrossedNotification thresholdCrossedNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);
}
