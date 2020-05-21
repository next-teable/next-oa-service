package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Project;

/**
 * @author dz
 */
public interface ProjectReference {

	boolean hasReference(Project target);

}
