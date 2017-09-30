package org.awesley.shoppinglist.resources.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.awesley.shoppinglist.resources.implementation.ShoppingListApiImpl;
import org.awesley.shoppinglist.resources.implementation.ShoppingListMapper;
import org.awesley.shoppinglist.resources.interfaces.IMapper;
import org.awesley.shoppinglist.resources.interfaces.ShoppingListApi;
import org.awesley.shoppinglist.resources.models.ShoppingList;

@Configuration
public class ResourcesConfiguration {

	@Bean
	public ShoppingListApi shoppingListApi(){
		return new ShoppingListApiImpl();
	}
	
	@Bean
	public IMapper<ShoppingList, org.awesley.shoppinglist.service.model.ShoppingList> shoppingListMapper(){
		return new ShoppingListMapper();
	}
}
