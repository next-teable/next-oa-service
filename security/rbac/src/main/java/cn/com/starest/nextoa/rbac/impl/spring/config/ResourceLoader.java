package cn.com.starest.nextoa.rbac.impl.spring.config;

import cn.com.starest.nextoa.rbac.core.model.ResourceWithChildren;

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
