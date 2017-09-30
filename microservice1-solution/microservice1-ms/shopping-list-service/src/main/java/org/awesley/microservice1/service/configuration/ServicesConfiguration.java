package org.awesley.microservice1.service.configuration;

import org.awesley.microservice1.service.implementation.Entity1GetService;
import org.awesley.microservice1.service.interfaces.IEntity1GetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

	@Bean
	public IEntity1GetService entity1GetService(){
		return new Entity1GetService();
	}
}
