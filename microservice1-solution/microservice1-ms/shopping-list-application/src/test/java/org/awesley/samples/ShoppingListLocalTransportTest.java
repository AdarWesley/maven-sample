package org.awesley.samples;

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
import org.awesley.shoppinglist.persistence.implementation.jpa.entities.JpaShoppingList;
import org.awesley.shoppinglist.persistence.implementation.jpa.repositories.ShoppingListJpaRepository;
import org.awesley.shoppinglist.resources.interfaces.ShoppingListApi;
import org.awesley.shoppinglist.resources.models.ShoppingList;
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
public class ShoppingListLocalTransportTest {

	private final static String ENDPOINT_ADDRESS = "local://services";
	private static Server server;

	private static List<Object> providers;
	
	@Autowired
	private void setShoppingListApi(ShoppingListApi shoppingListApi){
		ShoppingListLocalTransportTest.shoppingListApi = shoppingListApi;
	}
	
	private static ShoppingListApi shoppingListApi;
	
	@Autowired
	private ShoppingListJpaRepository shoppingListJpaRepository;
	
	@BeforeClass
	public static void initialize() throws Exception {
		initProviders();
	    startServer();
	}

	@Before
	public void initDatabase() {
		JpaShoppingList shoppingList = new JpaShoppingList();
		shoppingList.setListID("1");
		shoppingList.setName("My Test List");
		
		shoppingListJpaRepository.save(shoppingList);
	}

	private static void initProviders() {
		providers = new ArrayList<Object>();
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		jsonProvider.setMapper(new ObjectMapper());
		providers.add(jsonProvider);
	}

	private static void startServer() throws Exception {
	     JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	     sf.setResourceClasses(ShoppingListApi.class);
	         
	     sf.setProviders(providers);
	         
	     sf.setResourceProvider(ShoppingListApi.class,
	                            new SingletonResourceProvider(null){
	    	@Override
	    	public Object getInstance(Message m) {
	    		return shoppingListApi;
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
	public void canGetShoppingList() {
		ShoppingListApi client = JAXRSClientFactory.create(ENDPOINT_ADDRESS, ShoppingListApi.class, providers);
		WebClient.getConfig(client).getRequestContext().put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		
		ShoppingList shoppingList = client.getShoppingList("1");
		
		assertNotNull(shoppingList);
		assertEquals("1", shoppingList.getListID());	
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
