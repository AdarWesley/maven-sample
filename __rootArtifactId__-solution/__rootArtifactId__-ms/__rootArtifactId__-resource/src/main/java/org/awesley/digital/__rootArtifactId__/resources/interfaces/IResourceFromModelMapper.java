package org.awesley.digital.__rootArtifactId__.resources.interfaces;

public interface IResourceFromModelMapper<ResourceType, ModelType> {

	ResourceType mapFrom(ModelType modelEntity);

}
