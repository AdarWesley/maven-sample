package org.awesley.digital.__rootArtifactId__;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.message.Message;
import org.awesley.digital.__rootArtifactId__.config.TestConfiguration;
import org.awesley.digital.__rootArtifactId__.resources.interfaces.Entity1Api;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.contracttesting.ContractTestHelper;
import org.awesley.infra.contracttesting.ContractTestsExecutionListener;
import org.awesley.infra.contracttesting.SupportsTestHelper;
import org.awesley.infra.security.JwtTokenUtil;
import org.awesley.infra.security.model.JwtAuthority;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { 
	TestConfiguration.class
	//, CxfServiceSpringBootApplication.class 
	// , ContractTestsBase.LocalTransportConfiguration.class 
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners(value = { ContractTestsExecutionListener.class }, mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
public class ContractTestsBase implements SupportsTestHelper<ContractTestsBase> {

	// private final static String ENDPOINT_ADDRESS = "local://services";
    @LocalServerPort
    private int port;
    private Server server;
	private List<Object> providers;
	
	private ContractTestHelper<ContractTestsBase> contractTestHelper;
	
	@Autowired
    private Bus bus;
	
	@MockBean
	private IEntity1Repository entity1Repository;
	
	public IEntity1Repository getEntity1Repository() {
		return entity1Repository;
	}

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private void setEntity1Api(Entity1Api entity1Api){
		ContractTestsBase.entity1Api = entity1Api;
	}
	
	protected static Entity1Api entity1Api;
	
	private void initProviders() {
		providers = new ArrayList<Object>();
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		jsonProvider.setMapper(new ObjectMapper());
		providers.add(jsonProvider);
	}

	private void startServer() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setBus(bus);
		sf.setResourceClasses(Entity1Api.class);

		sf.setProviders(providers);

		sf.setResourceProvider(Entity1Api.class, new SingletonResourceProvider(null) {
			@Override
			public Object getInstance(Message m) {
				return entity1Api;
			}
		});

		sf.setAddress(
				"http://localhost:" + 
				port + 
				"/__rootArtifactId__-service");

		server = sf.create();
	}

	private RequestSpecification testRequestSpecification;
	
	public RequestSpecification given() {
		return testRequestSpecification;
	}
	
	private void initRestAssuredRequestSpecification() {
//		RestAssured.baseURI = "http://localhost"; 
//	    RestAssured.port = port;
	    
	    testRequestSpecification = RestAssured.given()
	    		.baseUri("http://localhost")
	    		.port(port);
	    
	    String mockToken = jwtTokenUtil.generateToken("TestUser", Arrays.asList(new JwtAuthority("ROLE_USER")));
	    testRequestSpecification.header("Authorization", "Bearer " + mockToken);
	}

	private void initMockRepository() {
		Entity1 entity1 = new Entity1() {
			@Override public Long getID() { return 1L; }
			@Override public void setID(Long id) {}
			@Override public String getName() {	return "TestUser"; }
			@Override public void setName(String name) {}
		};
			
		org.mockito.BDDMockito.given(entity1Repository.getById(1L)).willReturn(entity1);
		org.mockito.BDDMockito.given(entity1Repository.save(org.mockito.BDDMockito.any(Entity1.class)))
			.willAnswer(new Answer<Entity1>() {
				@Override
				public Entity1 answer(InvocationOnMock invocation) throws Throwable {
					Entity1 entity1 = (Entity1)invocation.getArgument(0);
					entity1.setID(1L);
					return entity1;
				}
			});
	}
	
	@Before
	public void BeforeTest() {
		initProviders();
	    startServer();
	    initMockRepository();
	    
	    initRestAssuredRequestSpecification();
	}

	@After
	public void destroy() throws Exception {
	   server.stop();
	   server.destroy();
	   providers = null;
	}

	@Override
	public ContractTestHelper<ContractTestsBase> getContractTestHelper() {
		return this.contractTestHelper;
	}

	@Override
	public void setContractTestsHelper(ContractTestHelper<ContractTestsBase> contractTestHelper) {
		this.contractTestHelper = contractTestHelper;
	}
}