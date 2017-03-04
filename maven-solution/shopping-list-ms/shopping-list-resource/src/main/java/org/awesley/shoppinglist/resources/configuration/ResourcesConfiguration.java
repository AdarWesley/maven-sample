package org.awesley.shoppinglist.resources.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.awesley.shoppinglist.resources.implementation.ShoppingListApiImpl;
import org.awesley.shoppinglist.resources.interfaces.ShoppingListApi;

@Configuration
public class ResourcesConfiguration {

	@Bean
	public ShoppingListApi shoppingListApi(){
		return new ShoppingListApiImpl();
	}
}
