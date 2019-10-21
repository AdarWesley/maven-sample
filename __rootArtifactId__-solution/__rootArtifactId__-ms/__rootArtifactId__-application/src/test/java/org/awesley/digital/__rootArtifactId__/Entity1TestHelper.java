package org.awesley.digital.__rootArtifactId__;

import java.util.Arrays;

import org.awesley.digital.__rootArtifactId__.persistence.implementation.jpa.entities.JpaEntity1;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1Repository;
import org.awesley.infra.contracttesting.AbstractContractTestHelper;
import org.awesley.infra.contracttesting.ContractTestsExecutionListener;
import org.awesley.infra.query.QueryExpression;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.TestContext;

public class Entity1TestHelper extends AbstractContractTestHelper<ContractTestsBase> {
	@Override
	public void beforeTestMethod(ContractTestsExecutionListener executionListener, TestContext testContext)
			throws Exception {
		
		IEntity1Repository entity1RepositoryMock = getTestInstance().getEntity1Repository();
		//BinaryQueryExpression findByNameExpresion = new BinaryQueryExpression("name", Operator.EQUALS, "Group1");
		JpaEntity1 mockEntity1 = new JpaEntity1();
		mockEntity1.setID(1L);
		mockEntity1.setName("Group1");
		org.mockito.BDDMockito.given(entity1RepositoryMock.find(ArgumentMatchers.any(QueryExpression.class), ArgumentMatchers.eq(0), ArgumentMatchers.eq(1)))
			.willAnswer(new Answer<Object>() {
				@Override
				public Object answer(InvocationOnMock invocation) throws Throwable {
					return Arrays.asList(mockEntity1);
				}
			});
	}
}
