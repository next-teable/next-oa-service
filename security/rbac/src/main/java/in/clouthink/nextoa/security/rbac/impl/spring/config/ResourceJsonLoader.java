package in.clouthink.nextoa.security.rbac.impl.spring.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.clouthink.nextoa.security.rbac.core.model.DefaultResourceWithChildren;
import in.clouthink.nextoa.security.rbac.core.model.ResourceWithChildren;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ResourceJsonLoader implements ResourceLoader {

	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<ResourceWithChildren> load(InputStream inputStream) {
		try {
			List<DefaultResourceWithChildren> parsedData = objectMapper.readValue(inputStream,
																				  new TypeReference<List<DefaultResourceWithChildren>>() {
																				  });

			parsedData.stream().forEach(resourceWithChildren -> linkParentAndChildren(resourceWithChildren));

			List<ResourceWithChildren> result = new ArrayList<>();
			parsedData.stream().forEach(resource -> {
				result.add(resource);
			});
			return result;
		}
		catch (Exception e) {
			throw new ResourceLoadException("加载资源出错", e);
		}
	}

	private void linkParentAndChildren(DefaultResourceWithChildren parent) {
		if (!parent.hasChildren()) {
			return;
		}

		parent.getChildren().forEach(child -> {
			((DefaultResourceWithChildren) child).setParent(parent);
			linkParentAndChildren((DefaultResourceWithChildren) child);
		});
	}


	@Override
	public List<ResourceWithChildren> load(File file) {
		try {
			return load(new FileInputStream(file));
		}
		catch (IOException e) {
			throw new ResourceLoadException(String.format("加载资源文件'%s'出错", file.getName()), e);
		}
	}

}
