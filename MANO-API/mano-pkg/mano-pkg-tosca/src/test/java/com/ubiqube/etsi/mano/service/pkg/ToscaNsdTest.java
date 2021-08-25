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
package com.ubiqube.etsi.mano.service.pkg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.tosca.ns.ToscaNsPackageProvider;
import com.ubiqube.etsi.mano.test.TestTools;

public class ToscaNsdTest {
	private final ToscaNsPackageProvider tpp;

	public ToscaNsdTest() throws IOException {
		final byte[] data = TestTools.readFile("/ubi-nsd-tosca.csar");
		tpp = new ToscaNsPackageProvider(data);
	}

	@Test
	void testNsInformations() throws Exception {
		final NsInformations list = tpp.getNsInformations(new HashMap<String, String>());
		assertEquals("flavor01", list.getFlavorId());
		assertEquals("demo", list.getInstantiationLevel());
		assertEquals(1, list.getMinNumberOfInstance());
		assertEquals(1, list.getMaxNumberOfInstance());
		assertEquals("ovi@ubiqube.com", list.getNsdDesigner());
		assertEquals("65f6fbed-654b-4d68-b730-5d8d72a8b865", list.getNsdInvariantId());
		assertEquals("ns01", list.getNsdName());
		assertEquals("0.0.1", list.getNsdVersion());
	}

	@Test
	void testNsVirtualLink() throws Exception {
		final Set<NsVirtualLink> list = tpp.getNsVirtualLink(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testNsSap() throws Exception {
		final Set<NsSap> list = tpp.getNsSap(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testSecurityGroupAdapter() throws Exception {
		final Set<SecurityGroupAdapter> list = tpp.getSecurityGroups(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testUbiqube01() throws Exception {
		final Set<String> list = tpp.getVnfd(new HashMap<String, String>());
		assertEquals(1, list.size());
	}

	@Test
	void testUbiqube02() throws Exception {
		final Set<String> list = tpp.getNestedNsd(new HashMap<String, String>());
		assertEquals(1, list.size());
	}
}
