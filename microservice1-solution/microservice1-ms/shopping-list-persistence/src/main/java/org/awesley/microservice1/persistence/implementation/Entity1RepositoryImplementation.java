package org.awesley.microservice1.persistence.implementation;

import org.awesley.microservice1.persistence.implementation.jpa.repositories.Entity1JpaRepository;
import org.awesley.microservice1.service.interfaces.IEntity1Repository;
import org.awesley.microservice1.service.model.Entity1;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1RepositoryImplementation implements IEntity1Repository {

	@Autowired
	private Entity1JpaRepository entity1JpaRepository;
	
	@Override
	public Entity1 getById(String id) {
		return entity1JpaRepository.findOne(id);
	}
}
