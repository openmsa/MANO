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
package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.service.graph.vnfm.StorageUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Start;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class StorageContributor extends AbstractVnfPlanContributor {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public StorageContributor(final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Storage.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage vnfPackage, final VnfBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}

		final List<VnfTask> ret = new ArrayList<>();
		plan.getTasks().stream()
				.filter(x -> x instanceof ComputeTask)
				.map(x -> (ComputeTask) x)
				.forEach(x -> {
					x.getVnfCompute().getStorages().forEach(y -> {
						final StorageTask task = new StorageTask();
						task.setToscaName(y);
						task.setType(ResourceTypeEnum.STORAGE);
						task.setAlias(y + "-" + x.getAlias() + "-" + RandomStringUtils.random(5, true, true));
						task.setParentAlias(x.getAlias());
						task.setVnfStorage(findVnfStorage(vnfPackage.getVnfStorage(), y));
						task.setChangeType(ChangeType.ADDED);
						ret.add(task);
					});
				});
		return ret;
	}

	private List<VnfTask> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, StorageTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final StorageTask task = new StorageTask();
			task.setToscaName(x.getTask().getToscaName());
			task.setType(ResourceTypeEnum.STORAGE);
			task.setParentAlias(x.getTask().getAlias());
			task.setChangeType(ChangeType.REMOVED);
			task.setRemovedVnfLiveInstance(x.getId());
			task.setVimResourceId(x.getResourceId());
			task.setVnfStorage(((StorageTask) x.getTask()).getVnfStorage());
			return task;
		}).collect(Collectors.toList());
	}

	private static VnfTask findCompute(final VnfBlueprint plan, final String toscaName) {
		return plan.getTasks().stream().filter(x -> x.getToscaName().equals(toscaName)).findAny().orElseThrow();
	}

	private static VnfStorage findVnfStorage(final Set<VnfStorage> vnfStorage, final String toscaName) {
		return vnfStorage.stream()
				.filter(x -> x.getToscaName().equals(toscaName))
				.findAny()
				.orElseThrow();
	}

	@Override
	public List<UnitOfWork<VnfTask, VnfParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint plan) {
		return tasks.stream()
				.filter(StorageTask.class::isInstance)
				.map(StorageTask.class::cast)
				.map(x -> new StorageUow(x, x.getVnfStorage()))
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Start.class).connectTo(Compute.class);
	}

}
