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
package com.ubiqube.etsi.mec.meo.v211.controller;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Configuration
public class SwaggerConfigurationMeo211 {
	@SuppressWarnings("static-method")
	@Bean
	public GroupedOpenApi customImplementationMeo211() {
		return GroupedOpenApi.builder()
				.group("mec-meo-2.1.1")
				.packagesToScan("com.ubiqube.etsi.mec.meo.v211")
				.build();
	}

}
