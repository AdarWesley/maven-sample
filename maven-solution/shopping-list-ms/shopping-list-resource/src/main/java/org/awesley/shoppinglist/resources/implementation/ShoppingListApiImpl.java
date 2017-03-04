package org.awesley.shoppinglist.resources.implementation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.awesley.shoppinglist.resources.interfaces.ShoppingListApi;
import org.awesley.shoppinglist.resources.models.ShoppingList;

import io.swagger.annotations.Api;

public class ShoppingListApiImpl implements ShoppingListApi {

	public ShoppingList getShoppingList(String id) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setListID(id);
		shoppingList.setName("List1");
		return shoppingList;
	}
}
