package org.awesley.digital.__rootArtifactId__.resources.interfaces;

public interface IResourceToModelMapper<ResourceType, ModelType> {
	ModelType mapTo(ResourceType resourceEntity);
}
