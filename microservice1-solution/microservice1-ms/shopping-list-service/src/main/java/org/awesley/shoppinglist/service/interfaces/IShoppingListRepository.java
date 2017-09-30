package org.awesley.shoppinglist.service.interfaces;

import org.awesley.shoppinglist.service.model.ShoppingList;

public interface IShoppingListRepository {
	ShoppingList getById(String ID);
}
