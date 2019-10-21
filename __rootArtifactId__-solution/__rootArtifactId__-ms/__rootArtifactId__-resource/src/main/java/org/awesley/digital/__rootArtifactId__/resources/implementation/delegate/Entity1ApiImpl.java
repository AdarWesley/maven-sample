package org.awesley.digital.__rootArtifactId__.resources.implementation.delegate;

import org.awesley.digital.__rootArtifactId__.resources.interfaces.IResourceFromModelMapper;
import org.awesley.digital.__rootArtifactId__.resources.interfaces.IResourceToModelMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.awesley.digital.__rootArtifactId__.resources.interfaces.Entity1Api;
import org.awesley.digital.__rootArtifactId__.resources.models.Entity1;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1CreateService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1FindService;
import org.awesley.digital.__rootArtifactId__.service.interfaces.IEntity1GetService;
import org.awesley.infra.query.QueryExpression;
import org.awesley.infra.query.parser.QueryExpressionParser;
import org.springframework.beans.factory.annotation.Autowired;

public class Entity1ApiImpl implements Entity1Api {

	@Autowired
	private IEntity1GetService entity1GetService;
	
	@Autowired
	private IEntity1FindService entity1FindService;
	
	@Autowired
	private IEntity1CreateService entity1CreateService;
	
	@Autowired
	private IResourceFromModelMapper<Entity1, org.awesley.digital.__rootArtifactId__.service.model.Entity1> entity1ResourceFromModelMapper;
	
	@Autowired
	private IResourceToModelMapper<Entity1, org.awesley.digital.__rootArtifactId__.service.model.Entity1> entity1ResourceToModelMapper;

	public Response getEntity1(Long id) {
		org.awesley.digital.__rootArtifactId__.service.model.Entity1 modelEntity1 = entity1GetService.GetEntity1(id);
		Entity1 entity1 = entity1ResourceFromModelMapper.mapFrom(modelEntity1);
		return Response.ok(entity1).build();
	}

	@Override
	public Response createEntity1(Entity1 entity1) {
		org.awesley.digital.__rootArtifactId__.service.model.Entity1 modelEntity1 = entity1ResourceToModelMapper.mapTo(entity1);
		modelEntity1 = entity1CreateService.createEntity1(modelEntity1);
		entity1 = entity1ResourceFromModelMapper.mapFrom(modelEntity1);
		
		return Response.status(Status.CREATED).entity(entity1).build();
	}

	@Override
	public Response findEntity1(String filter, Integer startIndex, Integer pageSize) {
		try {
			filter = URLDecoder.decode(filter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		QueryExpressionParser expressionParser = new QueryExpressionParser(filter);
		expressionParser.Parse();
		QueryExpression expression = expressionParser.getQueryExpression();
		List<? extends org.awesley.digital.__rootArtifactId__.service.model.Entity1> entity1List = 
				entity1FindService.find(expression, startIndex, pageSize);
		return Response.ok(entity1List.get(0)).build();
	}

	@Override
	public Response updateEntity1(Long id, Entity1 entity1) {
		// TODO Auto-generated method stub
		return null;
	}
}
