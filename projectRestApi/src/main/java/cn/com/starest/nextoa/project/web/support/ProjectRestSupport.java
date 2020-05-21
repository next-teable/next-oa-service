package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectRequest;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectRestSupport {

    /**
     * create new project
     *
     * @param request
     * @param byWho
     * @return
     */
    ProjectSummary createProject(SaveProjectRequest request, User byWho);

    /**
     * update the project
     *
     * @param id
     * @param request
     * @param byWho
     * @return
     */
    ProjectSummary updateProject(String id, SaveProjectRequest request, User byWho);

    /**
     * publish the project
     *
     * @param id
     * @param byWho
     */
    void publishProject(String id, User byWho);

    /**
     * close the project (end)
     *
     * @param id
     * @param user
     */
    void closeProject(String id, User user);

    /**
     * find project by id
     *
     * @param id
     * @param byWho
     * @return
     */
    ProjectDetail findProjectById(String id, User byWho);

    /**
     * list projects
     *
     * @param request
     * @param byWho
     * @return
     */
    Page<ProjectSummary> listProjects(ProjectQueryRequest request, User byWho);

    /**
     * delete the project
     *
     * @param id
     * @param user
     */
    void deleteProjectById(String id, User user);

    /**
     * @param id
     * @param request
     * @param user
     * @return
     */
    Page<ProjectHistorySummary> listProjectHistories(String id, PageQueryParameter request, User user);

    /**
     * @param id
     * @param user
     * @return
     */
    ProjectHistorySummary findProjectHistoryById(String id, User user);

    /**
     * @param id
     * @param user
     * @return
     */
    List<ProjectManageRelationshipSummary> listProjectManageRelationships(String id, User user);

    /**
     * @param request
     * @param user
     * @return
     */
    ProjectManageRelationshipSummary createProjectManageRelationship(SaveProjectManageRelationshipParameter request, User user);

    /**
     * @param id
     * @param request
     * @param user
     * @return
     */
    ProjectManageRelationshipSummary updateProjectManageRelationship(String id, SaveProjectManageRelationshipParameter request, User user);

    /**
     * @param id
     * @param user
     */
    void deleteProjectManageRelationship(String id, User user);
}
