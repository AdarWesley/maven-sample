package org.awesley.shoppinglist.service.configuration;

import org.awesley.shoppinglist.service.implementation.ShoppingListGetService;
import org.awesley.shoppinglist.service.interfaces.IShoppingListGetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

	@Bean
	public IShoppingListGetService shoppingListGetService(){
		return new ShoppingListGetService();
	}
}
