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
package com.ubiqube.etsi.mec.mepm.config;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;
import com.ubiqube.etsi.mano.dao.mec.dto.AppGrantRequest;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OrikaMepm implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(AppGrantRequest.class, VnfGrantsRequest.class)
				.field("appInstanceId", "vnfInstance.id")
				.field("appLcmOpOccId", "vnfLcmOpOccs.id")
				.field("appDId", "vnfdId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(AppBlueprint.class, VnfGrantsRequest.class)
				.field("appInstance", "vnfInstance")
				.field("id", "vnfLcmOpOccs.id")
				.field("appInstance.appPkg.appDId", "vnfdId")
				.byDefault()
				.register();

	}

}
