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
package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public interface VnfmRest {

	<T> T get(URI uri, Class<T> clazz);

	<T> T post(URI uri, Class<T> clazz);

	<T> T post(URI uri, Object body, Class<T> clazz);

	<T> T delete(URI uri, Class<T> clazz);

	<T> T call(URI uri, HttpMethod method, Class<T> clazz);

	<T> T call(URI uri, HttpMethod method, Object body, Class<T> clazz);

	<T> T get(URI uri, ParameterizedTypeReference<T> myBean);

	UriComponentsBuilder uriBuilder();

	RestTemplate getRestTemplate();

	MultiValueMap<String, String> getAutorization();

	<T> T get(final URI uri, final ResponseExtractor<T> responseExtractor);
}