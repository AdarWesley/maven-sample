package org.awesley.shoppinglist.service.implementation;

import org.awesley.shoppinglist.service.interfaces.IShoppingListGetService;
import org.awesley.shoppinglist.service.model.ShoppingList;

public class ShoppingListGetService implements IShoppingListGetService {

	public ShoppingList GetShoppingList(String id) {
		ShoppingList shoppingList = new ShoppingList();
		
		shoppingList.setListID(id);
		shoppingList.setName("MyList");
		
		return shoppingList;
	}

}
