package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveProjectCompletionRequest;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionSummary;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ProjectCompletionRestSupport {

	ProjectCompletionSummary createProjectCompletion(SaveProjectCompletionRequest request, User byWho);

	ProjectCompletionSummary updateProjectCompletion(String id, SaveProjectCompletionRequest request, User byWho);

	ProjectCompletionDetail findProjectCompletionById(String id, User byWho);

	Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request, User byWho);

	void deleteProjectCompletionById(String id, User user);

}
