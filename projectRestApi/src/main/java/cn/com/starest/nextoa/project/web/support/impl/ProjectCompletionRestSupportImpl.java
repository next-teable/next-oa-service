package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.request.SaveProjectCompletionRequest;
import cn.com.starest.nextoa.project.service.ProjectCompletionService;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionSummary;
import cn.com.starest.nextoa.project.web.support.ProjectCompletionRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectCompletionRestSupportImpl implements ProjectCompletionRestSupport {

	@Autowired
	private ProjectCompletionService projectCompletionService;

	@Override
	public ProjectCompletionSummary createProjectCompletion(SaveProjectCompletionRequest request, User byWho) {
		ProjectCompletion projectCompletion = projectCompletionService.createProjectCompletion(request, byWho);
		ProjectCompletionSummary summary = ProjectCompletionSummary.from(projectCompletion);
		summary.setGrantedActions(projectCompletionService.resolveGrantedActions(projectCompletion, byWho));
		return summary;
	}

	@Override
	public ProjectCompletionSummary updateProjectCompletion(String id,
															SaveProjectCompletionRequest request,
															User byWho) {
		ProjectCompletion projectCompletion = projectCompletionService.updateProjectCompletion(id, request, byWho);
		ProjectCompletionSummary summary = ProjectCompletionSummary.from(projectCompletion);
		summary.setGrantedActions(projectCompletionService.resolveGrantedActions(projectCompletion, byWho));
		return summary;
	}

	@Override
	public ProjectCompletionDetail findProjectCompletionById(String id, User byWho) {
		ProjectCompletion projectCompletion = projectCompletionService.findProjectCompletionById(id, byWho);
		ProjectCompletionDetail summary = ProjectCompletionDetail.from(projectCompletion);
		summary.setGrantedActions(projectCompletionService.resolveGrantedActions(projectCompletion, byWho));
		return summary;
	}

	@Override
	public Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request, User byWho) {
		Page<ProjectCompletion> projectCompletionPage = projectCompletionService.listProjectCompletions(request, byWho);
		return new PermissionAwarePageImpl<>(projectCompletionPage.getContent().stream().map(item -> {
			ProjectCompletionSummary summary = ProjectCompletionSummary.from(item);
			summary.setGrantedActions(projectCompletionService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 projectCompletionPage.getTotalElements(),
											 projectCompletionService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteProjectCompletionById(String id, User user) {
		projectCompletionService.deleteProjectCompletionById(id, user);
	}

}
