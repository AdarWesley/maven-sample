package org.awesley.digital.__rootArtifactId__.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.awesley.digital.__rootArtifactId__.persistence.configuration.PersistenceConfiguration;
import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.repositories.Entity1JpaRepository;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.digital.__rootArtifactId__.service.model.Entity1;
import org.awesley.infra.query.BinaryQueryExpression;
import org.awesley.infra.query.Operator;
import org.awesley.infra.query.QueryExpression;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		PersistenceTestConfiguration.class
		,PersistenceConfiguration.class	
	}
)
public class DbAccessTest {

	@Autowired
	private Entity1JpaRepository entity1JpaRepository;
	
	@Autowired
	private IEntity1Repository entity1Repository;
	
	@Test
	public void canQueryDatabase() {
		long count = entity1JpaRepository.count();
	}
	
	@Test
	public void canFindUserGroupByQueryExpression() {
		QueryExpression findExpression = new BinaryQueryExpression("name", Operator.EQUALS, "Entity11");
		
		List<? extends Entity1> list = entity1Repository.find(findExpression, 0, 1);
		
		assertNotNull(list);
		assertEquals(1, list.size());
	}}
