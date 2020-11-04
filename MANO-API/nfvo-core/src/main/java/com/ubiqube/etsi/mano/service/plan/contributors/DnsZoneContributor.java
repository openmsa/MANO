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
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.vnfm.DnsZoneUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.node.DnsZone;
import com.ubiqube.etsi.mano.service.vim.node.Node;

public class DnsZoneContributor implements PlanContributor {

	@Override
	public Class<? extends Node> getContributionType() {
		return DnsZone.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final DnsZoneTask dnsZoneTask = new DnsZoneTask();
		dnsZoneTask.setToscaName("zone");
		dnsZoneTask.setAlias(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setDomainName(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setChangeType(ChangeType.ADDED);
		dnsZoneTask.setType(ResourceTypeEnum.DNSZONE);
		return Collections.singletonList(dnsZoneTask);
	}

	@Override
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		final List<UnitOfWork> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof DnsZoneTask)
				.map(x -> (DnsZoneTask) x)
				.forEach(task -> {
					final DnsZoneUow dns = new DnsZoneUow(task);
					ret.add(dns);
				});
		return ret;
	}

}
