package org.awesley.shoppinglist.service.interfaces;

import org.awesley.shoppinglist.service.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {

}
