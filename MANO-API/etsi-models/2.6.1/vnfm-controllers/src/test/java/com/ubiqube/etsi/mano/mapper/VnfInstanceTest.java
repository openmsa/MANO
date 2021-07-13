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
package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ExtCpInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfInstanceTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfInstanceTest() {
		final OrikaMapperVnfm261 orikaConfiguration = new OrikaMapperVnfm261();
		final OrikaMapperVnfm261 orikaVnfm = new OrikaMapperVnfm261();
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaConfiguration.configure(mapperFactory);
		orikaVnfm.configure(mapperFactory);
		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVnfdId("1234");
		nsInstancesNsInstanceVnfInstance.setVnfInstanceDescription("description");
		nsInstancesNsInstanceVnfInstance.setVnfInstanceName("instance_name");
		nsInstancesNsInstanceVnfInstance.setVnfPkgId(UUID.fromString("3bba2147-147a-41ab-a3ec-1a39e1b6d922"));
		final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
		instantiatedVnfInfo.setFlavourId("flavour");
		instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STARTED);
		nsInstancesNsInstanceVnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);

		final com.ubiqube.etsi.mano.dao.mano.VnfInstance o = mapper.map(nsInstancesNsInstanceVnfInstance, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class);
		final BlueprintParameters ivi = o.getInstantiatedVnfInfo();
		assertNotNull(ivi);
		assertEquals("STARTED", ivi.getState().toString());
		assertNotNull(o.getVnfPkg());
		assertNotNull(o.getVnfPkg().getId());
		assertEquals("3bba2147-147a-41ab-a3ec-1a39e1b6d922", o.getVnfPkg().getId().toString());
	}

	@Test
	void testDaoToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance vnfInstance = new com.ubiqube.etsi.mano.dao.mano.VnfInstance();
		final BlueprintParameters instantiatedVnfInfo = new BlueprintParameters();
		vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
		vnfInstance.setInstantiationState(InstantiationState.INSTANTIATED);
		final NsdInstance nsInstance = new NsdInstance();
		instantiatedVnfInfo.setFlavourId("flavor");
		final NsdPackage nsdInfoId = new NsdPackage();
		nsdInfoId.setId(UUID.randomUUID());
		nsInstance.setNsdInfo(nsdInfoId);
		vnfInstance.setNsInstance(nsInstance);
		final Set<VimConnectionInformation> vimConnectionInfo = new HashSet<>();
		vimConnectionInfo.add(new VimConnectionInformation());
		vnfInstance.setVimConnectionInfo(vimConnectionInfo);
		final Map<String, String> extensions = new HashMap<>();
		vnfInstance.setExtensions(extensions);

		final VnfInstance o = mapper.map(vnfInstance, VnfInstance.class);
		assertNotNull(o.getInstantiatedVnfInfo());
	}

	@Test
	void testVnfInstance2LcmOpOccs() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfInstance avcDb = podam.manufacturePojo(VnfInstance.class);
		final VnfLcmOpOccs avc = mapper.map(avcDb, VnfLcmOpOccs.class);
		assertNotNull(avc.getError());
	}

	void testInstantiateInfo2VnfInstance() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfInstantiatedInfo vii = new VnfInstantiatedInfo();
		final Set<ExtCpInfo> extCpInfo = new HashSet<>();
		final ExtCpInfo extcpInfo = new ExtCpInfo();
		extcpInfo.setCpdId("abcdef");
		extCpInfo.add(extcpInfo);
		vii.setExtCpInfo(extCpInfo);
		final Set<VirtualLinkInfo> extManagedVirtualLinkInfo = new HashSet<>();
		extManagedVirtualLinkInfo.add(podam.manufacturePojo(VirtualLinkInfo.class));
		vii.setExtManagedVirtualLinkInfo(extManagedVirtualLinkInfo);
		vii.setFlavourId("abcd");
		vii.setLocalizationLanguage("abc");

		final Set<ExtVirtualLinkDataEntity> extVirtualLinkInfo = new HashSet<>();
		final VirtualLinkInfo vlii = new VirtualLinkInfo();
		extManagedVirtualLinkInfo.add(vlii);
		final ExtVirtualLinkDataEntity extVirtualLinkDataEntity = new ExtVirtualLinkDataEntity();
		extVirtualLinkDataEntity.setResourceId("zzzzz");
		extVirtualLinkInfo.add(extVirtualLinkDataEntity);
		vii.setExtVirtualLinkInfo(extVirtualLinkInfo);
		final VnfInstantiatedInfo avc = mapper.map(vii, VnfInstantiatedInfo.class);
		System.out.println("" + avc);
	}

	@Test
	void testInstantiateVnfRequest2VnfInstance() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final InstantiateVnfRequest instantiateVnfRequest = new InstantiateVnfRequest();
		instantiateVnfRequest.setFlavourId("eee");
		instantiateVnfRequest.setInstantiationLevelId("dfdd");
		instantiateVnfRequest.setLocalizationLanguage("aaa");
		final List<ExtVirtualLinkData> extVirtualLinks = new ArrayList<>();
		extVirtualLinks.add(podam.manufacturePojo(ExtVirtualLinkData.class));
		instantiateVnfRequest.setExtVirtualLinks(extVirtualLinks);
		final List<ExtManagedVirtualLinkData> extManagedVirtualLinks = new ArrayList<>();
		extManagedVirtualLinks.add(podam.manufacturePojo(ExtManagedVirtualLinkData.class));
		instantiateVnfRequest.setExtManagedVirtualLinks(extManagedVirtualLinks);
		final com.ubiqube.etsi.mano.dao.mano.VnfInstance avc = mapper.map(instantiateVnfRequest, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class);
		assertNotNull(avc.getExtVirtualLinks());

	}
}
