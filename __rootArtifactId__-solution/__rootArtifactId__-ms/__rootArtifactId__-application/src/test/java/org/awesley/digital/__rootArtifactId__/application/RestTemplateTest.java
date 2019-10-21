package org.awesley.digital.__rootArtifactId__.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.stream.Collectors;

import org.awesley.digital.__rootArtifactId__.config.TestConfiguration;
import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.digital.__rootArtifactId__.resources.models.Entity1;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { 
	TestConfiguration.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private JWTAuthorizationRequestInterceptor jwtAuthorizationRequestInterceptor;
	
	@MockBean
	private IEntity1Repository entity1Repository;
	
	public void initializeJWT() {
		List<ClientHttpRequestInterceptor> interceptors = testRestTemplate.getRestTemplate().getInterceptors();
		interceptors = 
				interceptors.stream().filter(i -> 
					(!(i instanceof BasicAuthorizationInterceptor) && 
					 !(i instanceof JWTAuthorizationRequestInterceptor)))
				.collect(Collectors.toList());
		interceptors.add(jwtAuthorizationRequestInterceptor);
		testRestTemplate.getRestTemplate().setInterceptors(interceptors);
	}
	
	private void initDatabase() {
		JpaEntity1 readEntity1 = new JpaEntity1();
		readEntity1.setID(1L);
		readEntity1.setName("My Test List");
		
		given(entity1Repository.getById(1L)).willReturn(readEntity1);
	}

	@Before
	public void initTest() {
		initializeJWT();
		initDatabase();
	}
	
	@Test
	@WithMockUser(username="TestAdmin", roles= {"ADMIN"})
	public void canLoadEntity1() {
		ResponseEntity<Entity1> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/__rootArtifactId__-service/entity1/1", Entity1.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}
