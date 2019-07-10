package org.awesley.digital.__rootArtifactId__.persistence.implementation;

import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.repositories.Entity1JpaRepository;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1RepositoryImplementation implements IEntity1Repository {

	@Autowired
	private Entity1JpaRepository entity1JpaRepository;
	
	@Override
	public Entity1 getById(Long id) {
		return entity1JpaRepository.findOne(id);
	}
	
	@Override
	public Entity1 save(Entity1 entity1) {
		return entity1JpaRepository.save((JpaEntity1)entity1);
	}
}
