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

/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ubiqube.etsi.mano.nfvo.v261.controller.nsd;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdmSubscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdmSubscriptionRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.SubscriptionsPostResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/sol005/nsd/v1/subscriptions")
@RolesAllowed({ "ROLE_OSSBSS" })
@Api(value = "subscriptions")
public interface NsdSubscriptions261Sol005Api {

	@ApiOperation(value = "Query multiple subscriptions.", nickname = "subscriptionsGet", notes = "The GET method queries the list of active subscriptions of the functional block that invokes the method. It can be used e.g. for resynchronization after error situations. This method shall support the URI query parameters, request and response data structures, and response codes. This resource represents subscriptions.  The client can use this resource to subscribe to notifications related to NSD management and to query its subscriptions. ", response = Object.class, responseContainer = "List", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK The list of subscriptions was queried successfully. The response body shall contain the representations of all active subscriptions of the functional block that invokes the method. ", response = Object.class, responseContainer = "List"),
			@ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the NSD file is returned. The response body shall contain the requested part of the NSD file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233 [23]. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response.        ", response = com.ubiqube.etsi.mano.model.ProblemDetails.class),
			@ApiResponse(code = 303, message = "303 See Other. A subscription with the same callbackURI and the same filter already exits and the policy of the NFVO is to not create redundant subscriptions. The HTTP response shall include a \"Location\" HTTP header that contains the resource URI of the existing subscription resource. The response body shall be empty. "),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<List<NsdmSubscription>> subscriptionsGet(@ApiParam(value = "Attribute filtering parameters according to clause 4.3.2. The NFVO shall support receiving attribute filter parameters as part of the URI query string. The OSS/BSS may supply an attribute filter. All attribute names that appear in the NsdmSubscription and in data types referenced from it shall be supported in attribute filter parameters. ") @Valid @RequestParam(value = "filter", required = false) String filter);

	@ApiOperation(value = "Subscribe to NSD and PNFD change notifications.", nickname = "subscriptionsPost", notes = "The POST method creates a new subscription.  This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 5.4.8.3.1-1 and 5.4.8.3.1-2 of GS-NFV SOL 005. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow creating a subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \"201 Created\" response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \"303 See Other\" response code referencing the existing subscription resource with the same filter and callbackUri). This resource represents subscriptions.  The client can use this resource to subscribe to notifications related to NSD management and to query its subscriptions.         ", response = SubscriptionsPostResponse.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "201 Created The subscription was created successfully. The response body shall contain a representation of the created subscription resource. The HTTP response shall include a \"Location:\" HTTP header that points to the created subscription resource.             ", response = SubscriptionsPostResponse.class),
			@ApiResponse(code = 206, message = "Partial Content. On success, if the NFVO supports range requests, a single consecutive byte range from the content of the NSD file is returned. The response body shall contain the requested part of the NSD file. The \"Content-Range\" HTTP header shall be provided according to IETF RFC 7233 [23]. The \"Content-Type\" HTTP header shall be set as defined above for the \"200 OK\" response.        ", response = ProblemDetails.class),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<NsdmSubscription> subscriptionsPost(@ApiParam(value = "", required = true) @RequestBody NsdmSubscriptionRequest body);

	@ApiOperation(value = "Terminate Subscription", nickname = "subscriptionsSubscriptionIdDelete", notes = "This resource represents an individual subscription.  It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The DELETE method terminates an individual subscription. This method shall support the URI query parameters, request and  response data structures, and response codes, as specified in the Table 5.4.9.3.3-2.       ", tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "204 No Content The subscription resource was deleted successfully. The response body shall be empty. "),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@DeleteMapping(value = "/{subscriptionId}", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> subscriptionsSubscriptionIdDelete(@ApiParam(value = "Identifier of this subscription.", required = true) @PathVariable("subscriptionId") String subscriptionId);

	@ApiOperation(value = "Read an individual subscription resource.", nickname = "subscriptionsSubscriptionIdGet", notes = "This resource represents an individual subscription.  It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The GET method retrieves information about a subscription by reading an individual subscription resource.  This resource represents an individual subscription.  It can be used by the client to read and to terminate a subscription to notifications related to NSD management. ", response = SubscriptionsPostResponse.class, tags = {})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "200 OK The operation has completed successfully. The response body shall contain a representation of the subscription resource.             ", response = SubscriptionsPostResponse.class),
			@ApiResponse(code = 400, message = "Error: Invalid attribute selector. The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 401, message = "Unauthorized If the request contains no access token even though one is required, or if the request contains an authorization token that is invalid (e.g. expired or revoked), the API producer should respond with this response. The details of the error shall be returned in the WWW-Authenticate HTTP header, as defined in IETF RFC 6750 and IETF RFC 7235. The ProblemDetails structure may be provided. ", response = ProblemDetails.class),
			@ApiResponse(code = 403, message = "Forbidden If the API consumer is not allowed to perform a particular request to a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure shall be provided.  It should include in the \"detail\" attribute information about the source of the problem, and may indicate how to solve it. ", response = ProblemDetails.class),
			@ApiResponse(code = 404, message = "Not Found If the API producer did not find a current representation for the resource addressed by the URI passed in the request, or is not willing to disclose that one exists, it shall respond with this response code.  The \"ProblemDetails\" structure may be provided, including in the \"detail\" attribute information about the source of the problem, e.g. a wrong resource URI variable. ", response = ProblemDetails.class),
			@ApiResponse(code = 405, message = "Method Not Allowed If a particular HTTP method is not supported for a particular resource, the API producer shall respond with this response code. The \"ProblemDetails\" structure may be omitted in that case. ", response = ProblemDetails.class),
			@ApiResponse(code = 406, message = "406 Not Acceptable If the \"Accept\" header does not contain at least one name of a content type for which the NFVO can provide a representation of the NSD, the NFVO shall respond with this response code. The \"ProblemDetails\" structure may be included with the \"detail\" attribute providing more information about the error.           ", response = ProblemDetails.class),
			@ApiResponse(code = 409, message = "Conflict Error: The operation cannot be executed currently, due to a conflict with the state of the resource. Typically, this is due to the fact the NS descriptor resource is in the enabled operational state (i.e. operationalState = ENABLED) or there are running NS instances using the concerned individual NS descriptor resource (i.e. usageState = IN_USE). The response body shall contain a ProblemDetails structure, in which the \"detail\" attribute shall convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 412, message = "Precondition Failed. A precondition given in an HTTP request header is not fulfilled. Typically, this is due to an ETag mismatch, indicating that the resource was modified by another entity.  The response body should contain a ProblemDetails structure, in which the \"detail\" attribute should convey more information about the error. ", response = ProblemDetails.class),
			@ApiResponse(code = 416, message = "The byte range passed in the \"Range\" header did not match any available byte range in the NSD file (e.g. access after end of file). The response body may contain a ProblemDetails structure. ", response = ProblemDetails.class),
			@ApiResponse(code = 500, message = "Internal Server Error If there is an application error not related to the client's input that cannot be easily mapped to any other HTTP response code (\"catch all error\"), the API producer shall respond with this response code. The ProblemDetails structure shall be provided, and shall include in the \"detail\" attribute more information about the source of the problem. ", response = ProblemDetails.class),
			@ApiResponse(code = 503, message = "Service Unavailable If the API producer encounters an internal overload situation of itself or of a system it relies on, it should respond with this response code, following the provisions in IETF RFC 7231 [13] for the use of the Retry-After HTTP header and for the alternative to refuse the connection. The \"ProblemDetails\" structure may be omitted. ", response = ProblemDetails.class) })
	@GetMapping(value = "/{subscriptionId}", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<NsdmSubscription> subscriptionsSubscriptionIdGet(@ApiParam(value = "Identifier of this subscription.", required = true) @PathVariable("subscriptionId") String subscriptionId);

}
