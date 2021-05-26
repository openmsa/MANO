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
package com.ubiqube.etsi.mano.nfvo.v281.controller.nsd;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.Valid;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.controller.nsd.NsDescriptorGenericFrontController;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.CreateNsdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.Link;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.NsdInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v281.model.nsd.NsdInfoModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NsDescriptors281Sol005Controller implements NsDescriptors281Sol005Api {
	private final NsDescriptorGenericFrontController nsDescriptorGenericFrontController;

	public NsDescriptors281Sol005Controller(final NsDescriptorGenericFrontController nsDescriptorGenericFrontController) {
		super();
		this.nsDescriptorGenericFrontController = nsDescriptorGenericFrontController;
	}

	@Override
	public ResponseEntity<String> nsDescriptorsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return nsDescriptorGenericFrontController.search(requestParams, NsdInfo.class, NsDescriptors281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdDelete(final String nsdInfoId) {
		return nsDescriptorGenericFrontController.delete(nsdInfoId);
	}

	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsNsdInfoIdGet(final String nsdInfoId) {
		return nsDescriptorGenericFrontController.finsById(nsdInfoId, NsdInfo.class, NsDescriptors281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> nsDescriptorsNsdInfoIdNsdContentGet(final String nsdInfoId, final String accept, final String range) {
		return nsDescriptorGenericFrontController.getNsdContent(nsdInfoId, accept, range);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdContentPut(final String nsdInfoId, final String accept, final MultipartFile file) {
		return nsDescriptorGenericFrontController.putNsdContent(nsdInfoId, accept, file);
	}

	@Override
	public ResponseEntity<NsdInfoModifications> nsDescriptorsNsdInfoIdPatch(final String nsdInfoId, @Valid final String body, final String ifMatch) {
		nsDescriptorGenericFrontController.modify(nsdInfoId, body, ifMatch, NsdInfo.class, NsDescriptors281Sol005Controller::makeLinks);
		final NsdInfoModifications modif = new NsdInfoModifications();
		return ResponseEntity.ok(modif);
	}

	@Override
	public ResponseEntity<NsdInfo> nsDescriptorsPost(@Valid final CreateNsdInfoRequest body) {
		return nsDescriptorGenericFrontController.create("", body.getUserDefinedData(), NsdInfo.class, NsDescriptors281Sol005Controller::makeLinks, NsDescriptors281Sol005Controller::makeSelfLink);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdManifestGet(final String nsdInfoId, @Valid final String includeSignatures) {
		return nsDescriptorGenericFrontController.getManifext(nsdInfoId, includeSignatures);
	}

	@Override
	public ResponseEntity<Void> nsDescriptorsNsdInfoIdNsdGet(final String nsdInfoId, @Valid final String includeSignatures) {
		return nsDescriptorGenericFrontController.getNsd(nsdInfoId, includeSignatures);
	}

	private static void makeLinks(@Nonnull final NsdInfo nsdInfo) {
		final String id = nsdInfo.getId();
		final NsdInfoLinks ret = new NsdInfoLinks();
		final Link nsdSelf = new Link();
		final String _self = makeSelfLink(nsdInfo);
		nsdSelf.setHref(_self);
		ret.setSelf(nsdSelf);

		final String _nsdContent = linkTo(methodOn(NsDescriptors281Sol005Api.class).nsDescriptorsNsdInfoIdNsdContentGet(id, "", "")).withSelfRel().getHref();
		final Link nsdContent = new Link();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);

		nsdInfo.setLinks(ret);
	}

	private static String makeSelfLink(final NsdInfo nsdInfo) {
		return linkTo(methodOn(NsDescriptors281Sol005Api.class).nsDescriptorsNsdInfoIdGet(nsdInfo.getId())).withSelfRel().getHref();
	}

}
