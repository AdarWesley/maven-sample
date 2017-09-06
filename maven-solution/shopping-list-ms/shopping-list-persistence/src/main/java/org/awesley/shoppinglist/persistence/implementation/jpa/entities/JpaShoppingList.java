package org.awesley.shoppinglist.persistence.implementation.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.awesley.shoppinglist.service.model.ShoppingList;

@Entity
@Table(name = "TBL_SHOPPING_LIST")
public class JpaShoppingList implements ShoppingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private String listID;
	
	@Column(name = "NAME")
	private String name;
	
	@Override
	public String getListID() {
		return listID;
	}

	@Override
	public void setListID(String listID) {
		this.listID = listID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
