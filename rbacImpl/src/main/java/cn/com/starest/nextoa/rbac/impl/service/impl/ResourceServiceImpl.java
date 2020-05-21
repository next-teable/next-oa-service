package cn.com.starest.nextoa.rbac.impl.service.impl;

import cn.com.starest.nextoa.rbac.core.model.Resource;
import cn.com.starest.nextoa.rbac.core.model.ResourceWithChildren;
import cn.com.starest.nextoa.rbac.core.spi.ResourceService;
import cn.com.starest.nextoa.rbac.impl.spring.config.ResourceJsonLoader;
import cn.com.starest.nextoa.rbac.impl.spring.config.ResourceLoader;
import cn.com.starest.nextoa.rbac.impl.support.ResourceAntMatcher;
import cn.com.starest.nextoa.rbac.impl.support.ResourceMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public class ResourceServiceImpl implements ResourceService, InitializingBean {

	private String resourceFile;

	private ResourceLoader resourceLoader = new ResourceJsonLoader();

	private ResourceMatcher resourceMatcher = new ResourceAntMatcher();

	private List<ResourceWithChildren> resourceList;

	private Map<String,ResourceWithChildren> resourceRepository = new HashMap<>();

	public String getResourceFile() {
		return resourceFile;
	}

	public void setResourceFile(String resourceFile) {
		this.resourceFile = resourceFile;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		Assert.notNull(resourceLoader);
		this.resourceLoader = resourceLoader;
	}

	public void setResourceMatcher(ResourceMatcher resourceMatcher) {
		Assert.notNull(resourceMatcher);
		this.resourceMatcher = resourceMatcher;
	}

	@Override
	public Resource findByCode(String code) {
		return resourceRepository.get(code);
	}

	@Override
	public List<Resource> getRootResources() {
		return Collections.unmodifiableList(resourceList);
	}

	@Override
	public List<Resource> getResourceChildren(Resource parent) {
		if (parent == null) {
			return Collections.unmodifiableList(Collections.emptyList());
		}
		ResourceWithChildren reloadedParent = resourceRepository.get(parent.getCode());
		if (reloadedParent.hasChildren()) {
			return Collections.unmodifiableList(reloadedParent.getChildren());
		}
		return Collections.unmodifiableList(Collections.emptyList());
	}

	@Override
	public Resource getFirstMatchedResource(String resourceUri) {
		return doMatchFirstResource(resourceUri, this.resourceList);
	}

	private Resource doMatchFirstResource(String resourceUri, List<? extends Resource> resources) {
		for (Resource resource : resources) {
			if (resourceMatcher.matched(resource, resourceUri)) {
				return resource;
			}
			if (resource instanceof ResourceWithChildren && (((ResourceWithChildren) resource).hasChildren())) {
				Resource matchedChild = doMatchFirstResource(resourceUri, ((ResourceWithChildren) resource).getChildren());
				if (matchedChild != null) {
					return matchedChild;
				}
			}
		}
		return null;
	}

	@Override
	public List<Resource> getMatchedResources(String resourceUri) {
		return resourceList.stream()
						   .filter(resource -> resourceMatcher.matched(resource, resourceUri))
						   .collect(Collectors.toList());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(resourceFile);
		Assert.notNull(resourceLoader);
		Assert.notNull(resourceMatcher);
		resourceList = resourceLoader.load(Resource.class.getClassLoader().getResourceAsStream(resourceFile));
		iterate(resourceList, resourceRepository);
	}

	private void iterate(List<? extends ResourceWithChildren> resourceList,
						 Map<String,ResourceWithChildren> resourceMap) {
		if (resourceList == null) {
			return;
		}
		resourceList.stream().forEach(resource -> {
			resourceMap.put(resource.getCode(), resource);
			if (resource.hasChildren()) {
				iterate(resource.getChildren(), resourceMap);
			}
		});
	}
}
