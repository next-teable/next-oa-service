package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ProjectType;

/**
 * @author dz
 */
public interface ProjectTypeReference {

	boolean hasReference(ProjectType target);

}
