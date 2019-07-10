package org.awesley.digital.__rootArtifactId__.service.interfaces;

import org.awesley.digital.__rootArtifactId__.service.model.Entity1;

public interface IEntity1Repository {
	Entity1 getById(Long ID);
	Entity1 save(Entity1 entity1);
}
