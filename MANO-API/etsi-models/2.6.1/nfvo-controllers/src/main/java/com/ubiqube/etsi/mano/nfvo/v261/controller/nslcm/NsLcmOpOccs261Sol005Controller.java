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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOccLinks;
import com.ubiqube.etsi.mano.service.NsBlueprintService;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_OSSBSS" })
@RestController
public class NsLcmOpOccs261Sol005Controller implements NsLcmOpOccs262Sol005Api {

	private static final String NSLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS = "operationParams,changedVnfInfo,error,resourceChanges";

	private static final Set<String> NSLCM_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "startTime", "operationState", "statusEnteredTime", "nsInstanceId", "lcmOperationType", "isAutomaticInvocation", "isCancelPending", "_links.continue.href", "_links.self.href", "_links.nsInstance.href", "_links.retry.href", "_links.rollback.href"));

	private final NsBlueprintService nsLcmOpOccsService;

	private final MapperFacade mapper;

	public NsLcmOpOccs261Sol005Controller(final NsBlueprintService _nsLcmOpOccsRepository, final MapperFacade _mapper) {
		nsLcmOpOccsService = _nsLcmOpOccsRepository;
		mapper = _mapper;
	}

	/**
	 * Query multiple NS LCM operation occurrences.
	 *
	 * Get Operation Status. The client can use this method to query status information about multiple NS lifecycle management operation occurrences. This method shall follow the provisions specified in the Tables 6.4.9.3.2-1 and 6.4.9.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<String> nsLcmOpOccsGet(final MultiValueMap<String, String> requestParams) {
		return nsLcmOpOccsService.search(requestParams, NsLcmOpOcc.class, NSLCM_SEARCH_DEFAULT_EXCLUDE_FIELDS, NSLCM_SEARCH_MANDATORY_FIELDS, NsLcmOpOccs261Sol005Controller::makeLinks);
	}

	/**
	 * Continue a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates continuing an NS lifecycle operation if that operation has experienced a temporary failure, i.e. the related \&quot;NS LCM operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This method shall follow the provisions specified in the Tables 6.4.13.3.1-1 and 6.4.13.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdContinuePost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Read an individual NS LCM operation occurrence resource.
	 *
	 * The client can use this method to retrieve status information about a NS lifecycle management operation occurrence by reading an individual \&quot;NS LCM operation occurrence\&quot; resource. This method shall follow the provisions specified in the Tables 6.4.10.3.2-1 and 6.4.10.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<NsLcmOpOcc> nsLcmOpOccsNsLcmOpOccIdGet(final String nsLcmOpOccId, final String contentType) {
		final NsBlueprint nsLcmOpOccs = nsLcmOpOccsService.findById(UUID.fromString(nsLcmOpOccId));
		final NsLcmOpOcc res = mapper.map(nsLcmOpOccs, NsLcmOpOcc.class);
		makeLinks(res);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Retry a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates retrying a NS lifecycle management operation if that operation has experienced a temporary failure, i.e. the related \&quot;NS LCM operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This method shall follow the provisions specified in the Tables 6.4.11.3.1-1 and 6.4.11.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRetryPost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Rollback a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates rolling back a NS lifecycle operation if that operation has experienced a temporary failure, i.e. the related \&quot;NS LCM operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This method shall follow the provisions specified in the Tables 6.4.12.3.1-1 and 6.4.12.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRollbackPost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	public static void makeLinks(@NotNull final NsLcmOpOcc nsLcmOpOccs) {
		@NotNull
		final String id = nsLcmOpOccs.getId().toString();
		final NsLcmOpOccLinks nsLcmOpOccLinks = new NsLcmOpOccLinks();

		final Link _continue = new Link();
		_continue.setHref(linkTo(methodOn(NsLcmOpOccs262Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdContinuePost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setContinue(_continue);

		final Link nsInstance = new Link();
		nsInstance.setHref(linkTo(methodOn(NsInstances261Sol005Api.class).nsInstancesNsInstanceIdGet(nsLcmOpOccs.getNsInstanceId())).withSelfRel().getHref());
		nsLcmOpOccLinks.setNsInstance(nsInstance);

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(NsLcmOpOccs262Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(NsLcmOpOccs262Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsLcmOpOccs262Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdGet(id, null)).withSelfRel().getHref());
		nsLcmOpOccLinks.setSelf(self);
		nsLcmOpOccs.setLinks(nsLcmOpOccLinks);
	}

	public static String makeSelfLink(final NsBlueprint nsLcmOpOccs) {
		final String id = nsLcmOpOccs.getId().toString();
		return linkTo(methodOn(NsLcmOpOccs262Sol005Api.class).nsLcmOpOccsNsLcmOpOccIdGet(id, null)).withSelfRel().getHref();
	}

}
