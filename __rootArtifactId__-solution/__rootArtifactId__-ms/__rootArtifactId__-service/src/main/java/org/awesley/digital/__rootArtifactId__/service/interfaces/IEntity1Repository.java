package org.awesley.digital.__rootArtifactId__.service.interfaces;

import java.util.List;

import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.query.QueryExpression;

public interface IEntity1Repository {
	Entity1 getById(Long ID);
	Entity1 save(Entity1 entity1);
	List<? extends Entity1> find(QueryExpression expression, Integer startIndex, Integer pageSize);
}
