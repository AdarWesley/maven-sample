package org.awesley.microservice1.application;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.awesley.microservice1.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.microservice1.persistence.implementation.jpa.repositories.Entity1JpaRepository;
import org.awesley.microservice1.resources.models.Entity1;
import org.junit.Before;
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
public class Microservice1Test {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private Entity1JpaRepository entity1JpaRepository;
	
	@Before
	public void initDatabase() {
		JpaEntity1 entity1 = new JpaEntity1();
		entity1.setID("1");
		entity1.setName("My Test List");
		
		entity1JpaRepository.save(entity1);
	}

	@Test
	public void canLoadEntity1() {
		ResponseEntity<Entity1> entity = testRestTemplate.getForEntity("http://localhost:" + this.port + "/services/entity1/1", Entity1.class);
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
