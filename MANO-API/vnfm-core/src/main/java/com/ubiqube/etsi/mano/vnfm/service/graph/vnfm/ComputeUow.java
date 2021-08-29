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
package com.ubiqube.etsi.mano.vnfm.service.graph.vnfm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ComputeUow extends VnfAbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	private final List<VnfLinkPort> vnfLinkPort;

	private final ComputeTask task;

	public ComputeUow(final ComputeTask _computeTask, final VnfCompute _vnfCompute, final Set<VnfLinkPort> _linkPort) {
		super(_computeTask);
		vnfCompute = _vnfCompute;
		vnfLinkPort = _linkPort.stream().collect(Collectors.toList());
		task = _computeTask;
	}

	@Override
	public String exec(final VnfParameters params) {
		final List<String> storages = vnfCompute.getStorages().stream().map(x -> params.getContext().get(x)).collect(Collectors.toList());
		final List<String> networks = vnfLinkPort.stream()
				.filter(x -> x.getVirtualBinding().equals(vnfCompute.getToscaName()))
				.map(VnfLinkPort::getVirtualLink)
				.map(x -> params.getContext().get(x))
				.collect(Collectors.toList());
		return params.getVim().createCompute(params.getVimConnectionInformation(), task.getAlias(), task.getFlavorId(), task.getImageId(), networks, storages, vnfCompute.getCloudInit());
	}

	@Override
	protected String getPrefix() {
		return "compute";
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().deleteCompute(params.getVimConnectionInformation(), params.getVimResourceId());
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		final List<WfDependency> ret = new ArrayList<>();
		final List<WfDependency> storages = vnfCompute.getStorages().stream()
				.map(x -> new WfDependency(Storage.class, x))
				.collect(Collectors.toList());
		final List<WfDependency> networks = vnfLinkPort.stream()
				.filter(x -> x.getVirtualBinding().equals(vnfCompute.getToscaName()))
				.map(VnfLinkPort::getVirtualLink)
				.map(x -> new WfDependency(Network.class, x))
				.collect(Collectors.toList());
		ret.addAll(networks);
		ret.addAll(storages);
		return ret;
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(Compute.class, task.getToscaName(), task.getId()));
	}

}
