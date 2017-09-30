package org.awesley.microservice1.resources.implementation;

import org.awesley.microservice1.resources.interfaces.IMapper;
import org.awesley.microservice1.resources.models.Entity1;

public class Entity1Mapper implements IMapper<Entity1, org.awesley.microservice1.service.model.Entity1> {

	@Override
	public Entity1 mapFrom(org.awesley.microservice1.service.model.Entity1 modelEntity) {
		Entity1 entity1 = new Entity1();
		
		entity1.setId(modelEntity.getID());
		entity1.setName(modelEntity.getName());
		
		return entity1;
	}

}
