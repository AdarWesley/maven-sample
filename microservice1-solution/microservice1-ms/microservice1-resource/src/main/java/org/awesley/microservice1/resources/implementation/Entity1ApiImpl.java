package org.awesley.microservice1.resources.implementation;

import org.awesley.microservice1.resources.interfaces.IMapper;
import org.awesley.microservice1.resources.interfaces.Entity1Api;
import org.awesley.microservice1.resources.models.Entity1;
import org.awesley.microservice1.service.interfaces.IEntity1GetService;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1ApiImpl implements Entity1Api {

	@Autowired
	private IEntity1GetService entity1GetService;
	
	@Autowired
	private IMapper<Entity1, org.awesley.microservice1.service.model.Entity1> entity1Mapper;
	
	public Entity1 getEntity1(String id) {
		org.awesley.microservice1.service.model.Entity1 modelEntity1 = entity1GetService.GetEntity1(id);
		Entity1 entity1 = entity1Mapper.mapFrom(modelEntity1);
		return entity1;
	}
}
