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
package com.ubiqube.etsi.mano.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.exception.PreConditionException;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfInstanceServiceImpl extends SearchableService implements VnfInstanceService {

	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final VnfInstanceJpa vnfInstanceJpa;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	private final EntityManager entityManager;

	private final Patcher patcher;

	private final EventManager eventManager;

	public VnfInstanceServiceImpl(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final VnfInstanceJpa _vnfInstanceJpa, final VnfLiveInstanceJpa _vnfLiveInstance,
			final EntityManager _entityManager, final Patcher _patcher, final EventManager _eventManager, final ManoSearchResponseService _searchService) {
		super(_searchService, _entityManager, VnfInstance.class);
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		vnfInstanceJpa = _vnfInstanceJpa;
		vnfLiveInstanceJpa = _vnfLiveInstance;
		entityManager = _entityManager;
		patcher = _patcher;
		eventManager = _eventManager;
	}

	@Override
	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	@Override
	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, vnfCompute.getToscaName());
	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.findByIdAndVnfInstance(vnfCompute.getId(), vnfInstance).stream().collect(Collectors.toCollection(ArrayDeque::new));
	}

	@Override
	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getToscaName());
	}

	@Override
	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, extCp.getToscaName());
	}

	@Override
	public int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId) {
		return vnfInstanceJpa.findByVnfPkg_IdAndNsInstance_Id(vnfPackageId, nsdInstance.getId()).orElseThrow(() -> new NotFoundException("Could not find vnf=" + vnfPackageId + " nsInstance=" + nsdInstance.getId()));
	}

	@Override
	public VnfInstance findById(final UUID id) {
		final VnfInstance inst = vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find VNF instance: " + id));
		inst.setInstantiationState(isLive(id));
		return inst;
	}

	private InstantiationState isLive(final UUID id) {
		return vnfLiveInstanceJpa.findByVnfInstanceId(id).isEmpty() ? InstantiationState.NOT_INSTANTIATED : InstantiationState.INSTANTIATED;
	}

	@Override
	public VnfInstance save(final VnfInstance vnfInstance) {
		return vnfInstanceJpa.save(vnfInstance);
	}

	@Override
	@Transactional
	public void delete(final UUID vnfInstanceId) {
		final VnfInstance entity = vnfInstanceJpa.findById(vnfInstanceId).orElseThrow(() -> new NotFoundException("Vnf Instance " + vnfInstanceId + " not found."));
		vnfInstanceJpa.delete(entity);
	}

	@Override
	public VnfLiveInstance findLiveInstanceByInstantiated(final UUID id) {
		return vnfLiveInstanceJpa.findByTaskId(id);
	}

	@Override
	public VnfLiveInstance save(final VnfLiveInstance vli) {
		return vnfLiveInstanceJpa.save(vli);
	}

	@Override
	public Optional<VnfLiveInstance> findLiveInstanceById(final UUID removedInstantiated) {
		return vnfLiveInstanceJpa.findById(removedInstantiated);
	}

	@Override
	public void deleteLiveInstanceById(final UUID id) {
		vnfLiveInstanceJpa.deleteById(id);
	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfBlueprint plan, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.findByTaskVnfInstanceAndToscaName(plan.getVnfInstance(), vnfCompute.getToscaName())
				.stream()
				.collect(Collectors.toCollection(ArrayDeque::new));
	}

	@Override
	public List<VnfLiveInstance> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfVlNotNull(vnfInstance);
	}

	@Override
	public List<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfComputeNotNull(vnfInstance);
	}

	@Override
	public List<VnfLiveInstance> getLiveExtCpInstanceOf(final VnfInstance vnfInstanceDb) {
		return vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstanceDb, ExternalCpTask.class.getSimpleName());
	}

	@Override
	public List<VnfLiveInstance> getLiveStorageInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfStorageIsNotNull(vnfInstance);
	}

	@Override
	public List<VnfInstance> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(entityManager);
		return sq.getCriteria(filter, VnfInstance.class);
	}

	@Override
	public boolean isInstantiate(final UUID id) {
		return 0 == vnfInstanceJpa.countByVnfPkgId(id);
	}

	@Override
	public VnfInstance vnfLcmPatch(final VnfInstance vnfInstance, final String body, final String ifMatch) {
		if ((ifMatch != null) && !ifMatch.equals(vnfInstance.getVersion() + "")) {
			throw new PreConditionException(ifMatch + " does not match " + vnfInstance.getVersion());
		}
		patcher.patch(body, vnfInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CHANGED, vnfInstance.getId());
		return vnfInstanceJpa.save(vnfInstance);
	}

}
