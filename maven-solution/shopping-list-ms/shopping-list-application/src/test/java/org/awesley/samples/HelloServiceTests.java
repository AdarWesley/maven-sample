package org.awesley.samples;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CxfServiceSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloServiceTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void callHelloService() {
		ResponseEntity<String> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/services/sayHello/adar", String.class);
		
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@org.springframework.boot.test.context.TestConfiguration
	static class TestConfiguration {
		
		@Bean
		@Primary
		public DataSource dataSource(){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
			dataSource.setUsername("sa");
			dataSource.setPassword("");
			dataSource.setDriverClassName("org.h2.Driver");
			return dataSource;
		}
		
	}
}
