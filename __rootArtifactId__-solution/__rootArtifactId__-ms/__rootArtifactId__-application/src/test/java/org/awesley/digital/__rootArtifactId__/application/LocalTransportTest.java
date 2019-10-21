package org.awesley.digital.__rootArtifactId__.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.impl.ResponseImpl;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.local.LocalConduit;
import org.awesley.digital.__rootArtifactId__.config.TestConfiguration;
import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.digital.__rootArtifactId__.resources.interfaces.Entity1Api;
import org.awesley.digital.__rootArtifactId__.resources.models.Entity1;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { 
	TestConfiguration.class
}) //, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalTransportTest {

	private final static String ENDPOINT_ADDRESS = "local://__rootArtifactId__-service";
	private static Server server;

	private static List<Object> providers;
	
	@Autowired
	private void setEntity1Api(Entity1Api entity1Api){
		LocalTransportTest.entity1Api = entity1Api;
	}
	
	private static Entity1Api entity1Api;
	
	@MockBean
	private IEntity1Repository entity1Repository;
	
	@BeforeClass
	public static void initialize() throws Exception {
		initProviders();
	    startServer();
	}

	@Before
	public void initDatabase() {
		JpaEntity1 entity1 = new JpaEntity1();
		entity1.setID(1L);
		entity1.setName("My Test Entity1");
		
		given(entity1Repository.getById(1L)).willReturn(entity1);
	}

	private static void initProviders() {
		providers = new ArrayList<Object>();
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		jsonProvider.setMapper(new ObjectMapper());
		providers.add(jsonProvider);
	}

	private static void startServer() throws Exception {
	     JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	     sf.setResourceClasses(Entity1Api.class);
	         
	     sf.setProviders(providers);
	         
	     sf.setResourceProvider(Entity1Api.class,
	                            new SingletonResourceProvider(null){
	    	@Override
	    	public Object getInstance(Message m) {
	    		return entity1Api;
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
		Entity1Api client = JAXRSClientFactory.create(ENDPOINT_ADDRESS, Entity1Api.class, providers);
		WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		
		ResponseImpl resp = (ResponseImpl) client.getEntity1(1L);
		Entity1 entity1 = resp.readEntity(Entity1.class);
		
		assertNotNull(entity1);
		assertEquals(1L, (long)entity1.getId());
	}
}
