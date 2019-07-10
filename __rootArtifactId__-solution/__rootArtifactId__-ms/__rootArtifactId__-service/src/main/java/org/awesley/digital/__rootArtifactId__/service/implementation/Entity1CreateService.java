package org.awesley.digital.__rootArtifactId__.service.implementation;

import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1CreateService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1CreateService implements IEntity1CreateService {

	@Autowired
	private IEntity1Repository entity1Repository;
	
	@Override
	public Entity1 createEntity1(Entity1 modelEntity1) {
		return entity1Repository.save(modelEntity1);
	}
}
