package org.awesley.microservice1.resources.interfaces;

public interface IMapper<ResourceType, ModelType> {

	ResourceType mapFrom(ModelType modelEntity);

}
