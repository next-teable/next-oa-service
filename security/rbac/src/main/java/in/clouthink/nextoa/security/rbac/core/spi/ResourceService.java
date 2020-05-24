package in.clouthink.nextoa.security.rbac.core.spi;

import in.clouthink.nextoa.security.rbac.core.model.Resource;

import java.util.List;

/**
 */
public interface ResourceService {

	Resource findByCode(String code);

	List<? extends Resource> getRootResources();

	List<? extends Resource> getResourceChildren(Resource parent);

	Resource getFirstMatchedResource(String resourceUri);

	List<? extends Resource> getMatchedResources(String resourceUri);

}
