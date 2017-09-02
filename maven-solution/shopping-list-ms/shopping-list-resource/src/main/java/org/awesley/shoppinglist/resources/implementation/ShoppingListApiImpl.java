package org.awesley.shoppinglist.resources.implementation;

import org.awesley.shoppinglist.resources.interfaces.IMapper;
import org.awesley.shoppinglist.resources.interfaces.ShoppingListApi;
import org.awesley.shoppinglist.resources.models.ShoppingList;
import org.awesley.shoppinglist.service.interfaces.IShoppingListGetService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingListApiImpl implements ShoppingListApi {

	@Autowired
	private IShoppingListGetService shoppingListGetService;
	
	@Autowired
	private IMapper<ShoppingList, org.awesley.shoppinglist.service.model.ShoppingList> shoppingListMapper;
	
	public ShoppingList getShoppingList(String id) {
		org.awesley.shoppinglist.service.model.ShoppingList modelShoppingList = shoppingListGetService.GetShoppingList(id);
		ShoppingList shoppingList = shoppingListMapper.mapFrom(modelShoppingList);
		return shoppingList;
	}
}
