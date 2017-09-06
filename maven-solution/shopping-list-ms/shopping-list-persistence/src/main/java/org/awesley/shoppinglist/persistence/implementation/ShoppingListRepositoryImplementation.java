package org.awesley.shoppinglist.persistence.implementation;

import org.awesley.shoppinglist.persistence.implementation.jpa.repositories.ShoppingListJpaRepository;
import org.awesley.shoppinglist.service.interfaces.IShoppingListRepository;
import org.awesley.shoppinglist.service.model.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingListRepositoryImplementation implements IShoppingListRepository {

	@Autowired
	private ShoppingListJpaRepository shoppingListJpaRepository;
	
	@Override
	public ShoppingList getById(String ID) {
		return shoppingListJpaRepository.findOne(ID);
	}
}
