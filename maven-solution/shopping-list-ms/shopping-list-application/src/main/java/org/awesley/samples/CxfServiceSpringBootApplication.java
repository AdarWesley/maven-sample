package org.awesley.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.awesley.shoppinglist.resources.configuration.ResourcesConfiguration;
import org.awesley.shoppinglist.service.configuration.ServicesConfiguration;
//import org.awesley.shoppinglist.resources.implementation.ShoppingListApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@SpringBootApplication(scanBasePackageClasses = { ResourcesConfiguration.class, ServicesConfiguration.class })
public class CxfServiceSpringBootApplication {
	
	@Autowired
	private ApplicationContext ctx;

	@Autowired
    private Bus bus;
	
    public static void main(String[] args) {
        SpringApplication.run(CxfServiceSpringBootApplication.class, args);
    }
  
    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        
		List<Object> providers = new ArrayList<Object>(ctx.getBeansWithAnnotation(Provider.class).values());
        endpoint.setProviders(providers);
        
        List<Object> serviceBeans = new ArrayList<Object>();
        serviceBeans.add(new HelloServiceImpl1());
        //serviceBeans.add(new ShoppingListApiImpl());
        serviceBeans.addAll(ctx.getBeansWithAnnotation(Path.class).values());
        
        // Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
        endpoint.setServiceBeans(serviceBeans);
        
        endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
        
        return endpoint.create();
    }
    
	@Bean
	@ConditionalOnMissingBean
	public JacksonJsonProvider jsonProvider(ObjectMapper objectMapper) {
		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
		provider.setMapper(objectMapper);
		return provider;
	}

	@Bean
	@ConditionalOnMissingBean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
    
}
