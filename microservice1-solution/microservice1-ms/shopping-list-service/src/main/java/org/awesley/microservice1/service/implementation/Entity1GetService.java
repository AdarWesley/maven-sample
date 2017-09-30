package org.awesley.microservice1.service.implementation;

import org.awesley.microservice1.service.interfaces.IEntity1GetService;
import org.awesley.microservice1.service.interfaces.IEntity1Repository;
import org.awesley.microservice1.service.model.Entity1;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1GetService implements IEntity1GetService {

	@Autowired
	private IEntity1Repository entity1Repository;
	
	public Entity1 GetEntity1(String id) {
		return entity1Repository.getById(id);
	}
}
