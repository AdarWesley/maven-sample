package org.awesley.shoppinglist.resources.interfaces;

public interface IMapper<ResourceType, ModelType> {

	ResourceType mapFrom(ModelType modelEntity);

}
