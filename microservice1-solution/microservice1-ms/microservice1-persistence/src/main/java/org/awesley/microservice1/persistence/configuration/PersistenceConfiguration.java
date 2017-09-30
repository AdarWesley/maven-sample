package org.awesley.microservice1.persistence.configuration;

import org.awesley.microservice1.persistence.implementation.Entity1RepositoryImplementation;
import org.awesley.microservice1.service.interfaces.IEntity1Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = { "org.awesley.microservice1.persistence.implementation.jpa.repositories" })
@EntityScan(basePackages = { "org.awesley.microservice1.persistence.implementation.jpa.entities" })
public class PersistenceConfiguration {

	@Bean
	public IEntity1Repository entity1Repository() {
		return new Entity1RepositoryImplementation();
	}
}
