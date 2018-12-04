package com.warriors.blogOnProject;



import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SmokeTest extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@MockBean
	private BaseOAuth2ProtectedResourceDetails resource;

	private OAuth2RestTemplate restTemplate;

	private AccessTokenProvider accessTokenProvider = Mockito.mock(AccessTokenProvider.class);

	private ClientHttpRequest request;

	private HttpHeaders headers;

	@Before
	public void open() throws Exception {
		resource = new BaseOAuth2ProtectedResourceDetails();
		// Facebook and older specs:
		resource.setTokenName("bearer_token");
		restTemplate = new OAuth2RestTemplate(resource);
		restTemplate.setAccessTokenProvider(accessTokenProvider);
		request = Mockito.mock(ClientHttpRequest.class);
		headers = new HttpHeaders();
		Mockito.when(request.getHeaders()).thenReturn(headers);
		ClientHttpResponse response = Mockito.mock(ClientHttpResponse.class);
		HttpStatus statusCode = HttpStatus.OK;
		Mockito.when(response.getStatusCode()).thenReturn(statusCode);
		Mockito.when(request.execute()).thenReturn(response);
	}
    
	@Test
	public void testNonBearerToken() throws Exception {
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken("0oah6lle5hBopHufX0h7");
		token.setTokenType("MINE");
		restTemplate.getOAuth2ClientContext().setAccessToken(token);
		System.out.println("" + restTemplate.getOAuth2ClientContext());
		OAuth2AccessToken newToken = restTemplate.getAccessToken();
		assertNotNull(newToken);
		/*ClientHttpRequest http = restTemplate.put(URI.create("https://nowhere.com/api/crap"), HttpMethod.GET);
		String auth = http.getHeaders().getFirst("Authorization");
		assertTrue(auth.startsWith("MINE "));*/
	}
	

}