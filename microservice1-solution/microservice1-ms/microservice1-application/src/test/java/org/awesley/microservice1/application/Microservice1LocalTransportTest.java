package org.awesley.microservice1.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.local.LocalConduit;
import org.awesley.microservice1.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.microservice1.persistence.implementation.jpa.repositories.Entity1JpaRepository;
import org.awesley.microservice1.resources.interfaces.Microservice1Api;
import org.awesley.microservice1.resources.models.Entity1;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CxfServiceSpringBootApplication.class) //, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Microservice1LocalTransportTest {

	private final static String ENDPOINT_ADDRESS = "local://services";
	private static Server server;

	private static List<Object> providers;
	
	@Autowired
	private void setMicroservice1Api(Microservice1Api Microservice1Api){
		Microservice1LocalTransportTest.Microservice1Api = Microservice1Api;
	}
	
	private static Microservice1Api Microservice1Api;
	
	@Autowired
	private Entity1JpaRepository Entity1JpaRepository;
	
	@BeforeClass
	public static void initialize() throws Exception {
		initProviders();
	    startServer();
	}

	@Before
	public void initDatabase() {
		JpaEntity1 entity1 = new JpaEntity1();
		entity1.setID("1");
		entity1.setName("My Test Entity1");
		
		Entity1JpaRepository.save(entity1);
	}

	private static void initProviders() {
		providers = new ArrayList<Object>();
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		jsonProvider.setMapper(new ObjectMapper());
		providers.add(jsonProvider);
	}

	private static void startServer() throws Exception {
	     JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	     sf.setResourceClasses(Microservice1Api.class);
	         
	     sf.setProviders(providers);
	         
	     sf.setResourceProvider(Microservice1Api.class,
	                            new SingletonResourceProvider(null){
	    	@Override
	    	public Object getInstance(Message m) {
	    		return Microservice1Api;
	    	}
	     });
	     
	     sf.setAddress(ENDPOINT_ADDRESS);
	 
	     server = sf.create();
	}
	 
	@AfterClass
	public static void destroy() throws Exception {
	   server.stop();
	   server.destroy();
	   providers = null;
	}

	@Test
	public void canGetEntity1() {
		Microservice1Api client = JAXRSClientFactory.create(ENDPOINT_ADDRESS, Microservice1Api.class, providers);
		WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		
		Entity1 entity1 = client.getEntity1("1");
		
		assertNotNull(entity1);
		assertEquals("1", entity1.getID());	
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
