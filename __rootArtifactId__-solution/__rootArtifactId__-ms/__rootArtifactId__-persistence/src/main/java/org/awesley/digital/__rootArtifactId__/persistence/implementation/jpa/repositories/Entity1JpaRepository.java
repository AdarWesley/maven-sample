package org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.repositories;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.query.QueryExpression;
import org.awesley.infra.query.jpa.QueryExpressionToJpaVisitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface Entity1JpaRepository extends CrudRepository<JpaEntity1, Long>, JpaSpecificationExecutor<JpaEntity1>  {

	default List<? extends Entity1> findByQueryExpression(QueryExpression expression, Integer startIndex, Integer pageSize) {
		
		Specification<JpaEntity1> querySpecification = new Specification<JpaEntity1>() {
			@Override
			public Predicate toPredicate(Root<JpaEntity1> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return QueryExpressionToJpaVisitor.baseConvert(root, query, cb, expression);
			}
		};
		
		Page<JpaEntity1> page = findAll(querySpecification, new PageRequest(startIndex / pageSize, pageSize));
		return page.getContent();
	}
}
