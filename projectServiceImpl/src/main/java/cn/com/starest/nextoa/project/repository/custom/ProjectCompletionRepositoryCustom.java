package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.request.ProjectCompletionQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ProjectCompletionRepositoryCustom {

	Page<ProjectCompletion> queryPage(ProjectCompletionQueryRequest request);

}
