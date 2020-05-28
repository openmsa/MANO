package com.ubiqube.etsi.mano.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.jpa.NsSapJpa;
import com.ubiqube.etsi.mano.jpa.NsVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;

@Service
public class NsInstanceService {
	private final NsSapJpa nsSapJpa;

	private final NsVirtualLinkJpa nsVirtualLinkJpa;

	private final NsdPackageJpa nsdPackageJpa;

	private final VnfPackageJpa vnfPackageJpa;

	public NsInstanceService(final NsSapJpa _nsSapJpa, final NsVirtualLinkJpa _nsVirtualLinkJpa, final NsdPackageJpa _nsdPackageJpa, final VnfPackageJpa _vnfPackageJpa) {
		nsSapJpa = _nsSapJpa;
		nsVirtualLinkJpa = _nsVirtualLinkJpa;
		nsdPackageJpa = _nsdPackageJpa;
		vnfPackageJpa = _vnfPackageJpa;
	}

	public int countLiveInstanceOfSap(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfVirtualLink(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfVnf(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int countLiveInstanceOfNsd(final NsdInstance nsInstance, final UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<NsSap> findSapsByNsInstance(final NsdPackage nsdInfo) {
		return nsSapJpa.findByNsdPackage(nsdInfo);
	}

	public Set<NsVirtualLink> findVlsByNsInstance(final NsdPackage nsdInfo) {
		return nsVirtualLinkJpa.findByNsdPackage(nsdInfo);
	}

	public Set<NsdPackage> findNestedNsdByNsInstance(final NsdPackage nsdInfo) {
		return nsdPackageJpa.findByNestedNsdInfoIds(nsdInfo);
	}

	public Set<VnfPackage> findVnfPackageByNsInstance(final NsdPackage nsdInfo) {
		return vnfPackageJpa.findByNsdPackages(nsdInfo);
	}

}
