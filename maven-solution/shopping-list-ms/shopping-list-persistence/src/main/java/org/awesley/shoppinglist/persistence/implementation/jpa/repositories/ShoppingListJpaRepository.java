package org.awesley.shoppinglist.persistence.implementation.jpa.repositories;

import org.awesley.shoppinglist.persistence.implementation.jpa.entities.JpaShoppingList;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListJpaRepository extends CrudRepository<JpaShoppingList, String> {

}
