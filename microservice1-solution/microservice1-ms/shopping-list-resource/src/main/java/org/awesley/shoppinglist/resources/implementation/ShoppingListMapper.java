package org.awesley.shoppinglist.resources.implementation;

import org.awesley.shoppinglist.resources.interfaces.IMapper;
import org.awesley.shoppinglist.resources.models.ShoppingList;

public class ShoppingListMapper implements IMapper<ShoppingList, org.awesley.shoppinglist.service.model.ShoppingList> {

	@Override
	public ShoppingList mapFrom(org.awesley.shoppinglist.service.model.ShoppingList modelEntity) {
		ShoppingList shoppingList = new ShoppingList();
		
		shoppingList.setListID(modelEntity.getListID());
		shoppingList.setName(modelEntity.getName());
		
		return shoppingList;
	}

}
