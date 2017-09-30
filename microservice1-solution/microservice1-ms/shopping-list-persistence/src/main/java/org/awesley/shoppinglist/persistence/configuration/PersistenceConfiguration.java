package org.awesley.shoppinglist.persistence.configuration;

import org.awesley.shoppinglist.persistence.implementation.ShoppingListRepositoryImplementation;
import org.awesley.shoppinglist.service.interfaces.IShoppingListRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = { "org.awesley.shoppinglist.persistence.implementation.jpa.repositories" })
@EntityScan(basePackages = { "org.awesley.shoppinglist.persistence.implementation.jpa.entities" })
public class PersistenceConfiguration {

	@Bean
	public IShoppingListRepository shoppingListRepository() {
		return new ShoppingListRepositoryImplementation();
	}
}
