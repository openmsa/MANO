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

package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.common.v261.VnfSubscriptionFactory;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol003Linkable;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol005Linkable;
import com.ubiqube.etsi.mano.service.VersionService;
import com.ubiqube.etsi.mano.service.rest.VnfmRest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

import ma.glasnost.orika.MapperFacade;

@Service
public class Nfvo261VersionService implements VersionService {

	private final VnfmRest vnfmRest;
	private final MapperFacade mapper;

	public Nfvo261VersionService(final VnfmRest _vnfmRest, final MapperFacade _mapper) {
		vnfmRest = _vnfmRest;
		mapper = _mapper;
	}

	@Override
	public String getVersion() {
		return "2.6.1";
	}

	@Override
	public boolean isNfvo() {
		return true;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final var obj = VnfSubscriptionFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId, null, new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createVnfPackageOnboardingNotificationLinks(vnfPkgId, subscriptionId));
		return obj;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final var obj = VnfSubscriptionFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId, null, new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createNotificationLink(vnfPkgId, subscriptionId));
		return obj;
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(final UUID vnfdId) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", vnfdId);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_lcm_op_occs/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		final var res = vnfmRest.get(uri, VnfLcmOpOcc.class);
		return mapper.map(res, VnfBlueprint.class);
	}

	@Override
	public List<VnfInstance> vnfInstanceGet(final MultiValueMap<String, String> params) {
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances")
				.queryParams(params)
				.build()
				.toUri();
		final ParameterizedTypeReference<List<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance>> myBean = new ParameterizedTypeReference<>() {
		};
		final List<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> res = vnfmRest.get(uri, myBean);
		return res.stream().map(x -> mapper.map(x, VnfInstance.class)).collect(Collectors.toList());
	}

	@Override
	public VnfInstance vnfInstancePost(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		final var req = new CreateVnfRequest();
		req.setVnfdId(vnfdId);
		req.setVnfInstanceDescription(vnfInstanceDescription);
		req.setVnfInstanceName(vnfInstanceName);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances")
				.build()
				.toUri();
		final var res = vnfmRest.post(uri, req, com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class);
		return mapper.map(res, VnfInstance.class);
	}

	@Override
	public VnfBlueprint vnfInstanceOperate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		final var operateRequest = mapper.map(operateVnfRequest, OperateVnfRequest.class);
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", uuid);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.post(uri, operateRequest, Void.class);
		// XXX At this level we are returning nothing.
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceScale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		final var scaleRequest = mapper.map(scaleVnfRequest, ScaleVnfRequest.class);
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", uuid);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.post(uri, scaleRequest, Void.class);
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceScaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final var scaleRequest = mapper.map(scaleVnfToLevelRequest, ScaleVnfToLevelRequest.class);
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", uuid);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.post(uri, scaleRequest, Void.class);
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceTerminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final var terminateVnfRequest = new TerminateVnfRequest();
		terminateVnfRequest.setGracefulTerminationTimeout(gracefulTerminationTimeout);
		terminateVnfRequest.setTerminationType(TerminationTypeEnum.valueOf(terminationType.toString()));
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", vnfInstanceId);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.post(uri, terminateVnfRequest, Void.class);
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceInstantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		final var request = mapper.map(instantiateVnfRequest, InstantiateVnfRequest.class);
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", vnfInstanceId);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.post(uri, request, Void.class);
		return null;
	}

	@Override
	public void vnfInstanceDelete(final UUID vnfInstanceId) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("id", vnfInstanceId);
		final var uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.delete(uri, null);
	}

}
