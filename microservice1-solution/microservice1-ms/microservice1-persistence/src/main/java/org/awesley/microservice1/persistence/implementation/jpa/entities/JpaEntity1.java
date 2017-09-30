package org.awesley.microservice1.persistence.implementation.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.awesley.microservice1.service.model.Entity1;

@Entity
@Table(name = "TBL_SHOPPING_LIST")
public class JpaEntity1 implements Entity1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private String entityID;
	
	@Column(name = "NAME")
	private String name;
	
	@Override
	public String getID() {
		return entityID;
	}

	@Override
	public void setID(String id) {
		this.entityID = id;
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
