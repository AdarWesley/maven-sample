package org.awesley.digital.__rootArtifactId__.service.interfaces;

import java.util.List;

import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.query.QueryExpression;

public interface IEntity1FindService {

	List<? extends Entity1> find(QueryExpression expression, Integer startIndex, Integer pageSize);

}
