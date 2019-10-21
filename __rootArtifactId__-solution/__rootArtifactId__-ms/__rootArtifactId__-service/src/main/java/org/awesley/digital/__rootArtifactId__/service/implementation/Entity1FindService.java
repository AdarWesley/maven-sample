package org.awesley.digital.__rootArtifactId__.service.implementation;

import java.util.List;

import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1FindService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.query.QueryExpression;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1FindService implements IEntity1FindService {

	@Autowired
	private IEntity1Repository entity1Repository;

	@Override
	public List<? extends Entity1> find(QueryExpression expression, Integer startIndex, Integer pageSize) {
		List<? extends Entity1> entity1List = entity1Repository.find(expression, startIndex, pageSize);
		return entity1List;
	}

}
