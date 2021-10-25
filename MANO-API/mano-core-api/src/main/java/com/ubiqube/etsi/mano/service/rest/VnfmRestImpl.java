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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.config.properties.VnfmConnectionProperties;
import com.ubiqube.etsi.mano.config.properties.VnfmConnectionProperties.Basic;
import com.ubiqube.etsi.mano.config.properties.VnfmConnectionProperties.Oauth2;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * Rest Calls from NFVO to VFNM.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmRestImpl extends AbstractRest implements VnfmRest {
	private final String url;
	private final MultiValueMap<String, String> auth = new HttpHeaders();

	public VnfmRestImpl(final VnfmConnectionProperties props) {
		url = props.getUrl();
		if (null != props.getOauth2()) {
			final Oauth2 oauth = props.getOauth2();
			final OAuth2ProtectedResourceDetails resource = getResourceDetails(oauth);
			final var oauth2 = new OAuth2RestTemplate(resource);
			disableSsl(oauth2);
			setRestTemplate(oauth2);
		}
		if (props.getBasic() != null) {
			final Basic basic = props.getBasic();
			final String user = basic.getUsername();
			if (null != user) {
				final String password = Optional.of(basic.getPassword()).orElse("");
				auth.add("Authorization", authBasic(user, password));
			}
		}
		Assert.notNull(url, "vnfm.url is not declared in property file.");
	}

	private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = {
			new X509TrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
					//
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
					//
				}
			}
	};

	private void disableSsl(final OAuth2RestTemplate oauth2) {
		try {
			final SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (KeyManagementException | NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private OAuth2ProtectedResourceDetails getResourceDetails(final Oauth2 oauth) {
		if ("passsword".equals(oauth.getGrantType())) {
			final var resource = new ResourceOwnerPasswordResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getOauthUrl());
			resource.setUsername(oauth.getUsername());
			resource.setPassword(oauth.getPassword());
			return resource;
		}
		if ("client_credentials".equals(oauth.getGrantType())) {
			final var resource = new ClientCredentialsResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getOauthUrl());
			resource.setClientAuthenticationScheme(AuthenticationScheme.form);
			return resource;
		}
		throw new GenericException("Unable to find correct grant type: " + oauth.getGrantType());
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	public MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
