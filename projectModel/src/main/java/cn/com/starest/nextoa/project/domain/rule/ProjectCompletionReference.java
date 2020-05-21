package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;

/**
 * @author dz
 */
public interface ProjectCompletionReference {

	boolean hasReference(ProjectCompletion target);

}
