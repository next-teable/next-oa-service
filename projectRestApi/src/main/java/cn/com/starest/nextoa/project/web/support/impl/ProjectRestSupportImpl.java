package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectHistory;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectRequest;
import cn.com.starest.nextoa.project.service.ProjectService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectRestSupportImpl implements ProjectRestSupport {

    @Autowired
    private ProjectService projectService;

    @Override
    public ProjectSummary createProject(SaveProjectRequest request, User byWho) {
        Project project = projectService.createProject(request, byWho);
        ProjectSummary result = ProjectSummary.from(project);
        result.setGrantedActions(projectService.resolveGrantedActions(project, byWho));
        return result;
    }

    @Override
    public ProjectSummary updateProject(String id, SaveProjectRequest request, User byWho) {
        Project project = projectService.updateProject(id, request, byWho);
        ProjectSummary result = ProjectSummary.from(project);
        result.setGrantedActions(projectService.resolveGrantedActions(project, byWho));
        return result;
    }

    @Override
    public void publishProject(String id, User byWho) {
        projectService.publishProject(id, byWho);
    }

    @Override
    public void closeProject(String id, User byWho) {
        projectService.closeProject(id, byWho);
    }

    @Override
    public ProjectDetail findProjectById(String id, User byWho) {
        Project project = projectService.findProjectById(id, byWho);
        ProjectDetail result = ProjectDetail.from(project);
        result.setGrantedActions(projectService.resolveGrantedActions(project, byWho));
        return result;
    }

    @Override
    public Page<ProjectSummary> listProjects(ProjectQueryRequest request, User byWho) {
        Page<Project> projectPage = projectService.listProjects(request, byWho);
        return new PermissionAwarePageImpl<>(projectPage.getContent().stream().map(project -> {
            ProjectSummary projectSummary = ProjectSummary.from(project);
            projectSummary.setGrantedActions(projectService.resolveGrantedActions(project, byWho));
            return projectSummary;
        }).collect(Collectors.toList()),
                new PageRequest(request.getStart(), request.getLimit()),
                projectPage.getTotalElements(),
                projectService.resolveGrantedActions(byWho));
    }

    @Override
    public void deleteProjectById(String id, User user) {
        projectService.deleteProjectById(id, user);
    }

    @Override
    public Page<ProjectHistorySummary> listProjectHistories(String id, PageQueryParameter request, User byWho) {
        Page<ProjectHistory> projectHistoryPage = projectService.listProjectHistories(id, request);
        return new PageImpl<>(projectHistoryPage.getContent()
                .stream()
                .map(ProjectHistorySummary::from)
                .collect(Collectors.toList()),
                new PageRequest(request.getStart(), request.getLimit()),
                projectHistoryPage.getTotalElements());
    }

    @Override
    public ProjectHistorySummary findProjectHistoryById(String id, User user) {
        return ProjectHistorySummary.from(projectService.findProjectHistoryById(id));
    }

    @Override
    public List<ProjectManageRelationshipSummary> listProjectManageRelationships(String id, User user) {
        List<ProjectManageRelationship> models = projectService.listProjectManageRelationships(id, user);
        return models.stream().map(ProjectManageRelationshipSummary::from).collect(Collectors.toList());
    }

    @Override
    public ProjectManageRelationshipSummary createProjectManageRelationship(SaveProjectManageRelationshipParameter request, User user) {
        return ProjectManageRelationshipSummary.from(projectService.createProjectManageRelationship(request, user));
    }

    @Override
    public ProjectManageRelationshipSummary updateProjectManageRelationship(String id, SaveProjectManageRelationshipParameter request, User user) {
        return ProjectManageRelationshipSummary.from(projectService.updateProjectManageRelationship(id, request, user));
    }

    @Override
    public void deleteProjectManageRelationship(String id, User user) {
        projectService.deleteProjectManageRelationship(id, user);
    }

}

