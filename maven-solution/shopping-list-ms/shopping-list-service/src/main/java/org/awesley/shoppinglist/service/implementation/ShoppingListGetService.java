package org.awesley.shoppinglist.service.implementation;

import org.awesley.shoppinglist.service.interfaces.IShoppingListGetService;
import org.awesley.shoppinglist.service.interfaces.IShoppingListRepository;
import org.awesley.shoppinglist.service.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingListGetService implements IShoppingListGetService {

	@Autowired
	private IShoppingListRepository shoppingListRepository;
	
	public ShoppingList GetShoppingList(String id) {
		return shoppingListRepository.getById(id);
	}
}
