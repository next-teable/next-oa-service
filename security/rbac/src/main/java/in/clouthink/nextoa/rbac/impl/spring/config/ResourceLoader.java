package in.clouthink.nextoa.rbac.impl.spring.config;

import in.clouthink.nextoa.rbac.core.model.ResourceWithChildren;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 */
public interface ResourceLoader {

	List<ResourceWithChildren> load(InputStream inputStream);

	List<ResourceWithChildren> load(File file);

}
