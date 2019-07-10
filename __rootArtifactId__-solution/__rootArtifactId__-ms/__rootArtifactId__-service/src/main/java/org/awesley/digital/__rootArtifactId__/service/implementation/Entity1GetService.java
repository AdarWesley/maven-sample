package org.awesley.digital.__rootArtifactId__.service.implementation;

import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1GetService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1GetService implements IEntity1GetService {

	@Autowired
	private IEntity1Repository entity1Repository;
	
	public Entity1 GetEntity1(Long id) {
		return entity1Repository.getById(id);
	}
}
