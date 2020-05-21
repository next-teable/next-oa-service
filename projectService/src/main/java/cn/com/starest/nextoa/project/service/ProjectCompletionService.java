package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.request.ProjectCompletionQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectCompletionRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectCompletionService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(ProjectCompletion projectCompletion, User byWho);

	ProjectCompletion createProjectCompletion(SaveProjectCompletionRequest request, User byWho);

	ProjectCompletion updateProjectCompletion(String id, SaveProjectCompletionRequest request, User byWho);

	ProjectCompletion findProjectCompletionById(String id, User byWho);

	void deleteProjectCompletionById(String id, User byWho);

	Page<ProjectCompletion> listProjectCompletions(ProjectCompletionQueryRequest request, User byWho);

	List<ProjectCompletion> listProjectCompletionsByProject(String projectId, User byWho);

	List<ProjectCompletion> listProjectCompletionsByProject(Project project, User byWho);

}
