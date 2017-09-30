package org.awesley.microservice1.resources.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.awesley.microservice1.resources.implementation.Entity1ApiImpl;
import org.awesley.microservice1.resources.implementation.Entity1Mapper;
import org.awesley.microservice1.resources.interfaces.IMapper;
import org.awesley.microservice1.resources.interfaces.Entity1Api;
import org.awesley.microservice1.resources.models.Entity1;

@Configuration
public class ResourcesConfiguration {

	@Bean
	public Entity1Api entity1Api(){
		return new Entity1ApiImpl();
	}
	
	@Bean
	public IMapper<Entity1, org.awesley.microservice1.service.model.Entity1> entity1Mapper(){
		return new Entity1Mapper();
	}
}
