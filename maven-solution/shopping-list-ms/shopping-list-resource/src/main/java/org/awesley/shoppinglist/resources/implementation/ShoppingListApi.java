package org.awesley.shoppinglist.resources.implementation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.awesley.shoppinglist.resources.models.ShoppingList;

public class ShoppingListApi implements org.awesley.shoppinglist.resources.interfaces.ShoppingListApi {

	public Response getShoppingList(String id) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setListID(id);
		shoppingList.setName("List1");
		return Response.ok(shoppingList, MediaType.APPLICATION_JSON).build();
	}

}
