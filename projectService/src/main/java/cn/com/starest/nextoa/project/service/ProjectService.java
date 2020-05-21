package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectHistory;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectManageRelationshipRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * 项目管理相关服务
 *
 * @author dz
 */
public interface ProjectService {

    /**
     * @param byWho
     * @return
     */
    ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

    /**
     * @param project
     * @param byWho
     * @return
     */
    ModuleActions.ModuleAction[] resolveGrantedActions(Project project, User byWho);

    /**
     * 创建项目
     *
     * @param request
     * @param byWho
     * @return
     */
    Project createProject(SaveProjectRequest request, User byWho);

    /**
     * 修改项目 (如果非草稿,修改的时候需要记录修改历史记录)
     *
     * @param projectId
     * @param request
     * @param byWho
     * @return
     */
    Project updateProject(String projectId, SaveProjectRequest request, User byWho);

    /**
     * 发布项目（项目从草稿变为进行中）
     *
     * @param projectId
     * @param byWho
     * @return
     */
    Project publishProject(String projectId, User byWho);

    /**
     * 结束项目
     *
     * @param projectId
     * @param byWho
     * @return
     */
    Project closeProject(String projectId, User byWho);


    /**
     * 查找项目
     *
     * @param projectId
     * @param byWho
     * @return
     */
    Project findProjectById(String projectId, User byWho);

    /**
     * 删除项目（只有草稿能删除）
     *
     * @param projectId
     * @param byWho
     */
    void deleteProjectById(String projectId, User byWho);

    /**
     * 查找项目
     *
     * @param request
     * @param byWho
     * @return
     */
    Page<Project> listProjects(ProjectQueryRequest request, User byWho);

    /**
     * 查找项目
     *
     * @param request
     * @param byWho
     * @return
     */
    Page<Project> listManagedProjects(ProjectQueryRequest request, User byWho);

    /**
     * 查找项目
     *
     * @param request
     * @param byWho
     * @return
     */
    Page<Project> listSupervisedProjects(ProjectQueryRequest request, User byWho);

    /**
     * 查找项目管理关系
     * @param request
     * @param manager
     * @return
     */
    Page<ProjectManageRelationship> listSupervisedProjectManageRelationship(ProjectQueryRequest request, User manager);

    /**
     * 根据业务部门查找项目管理关系
     * @param bizDepartmentId
     * @return
     */
    List<ProjectManageRelationship> listSupervisedProjectManageRelationship(String bizDepartmentId);

    /**
     * 查找项目历史
     *
     * @param projectId
     * @param pageQueryRequest
     * @return
     */
    Page<ProjectHistory> listProjectHistories(String projectId, PageQueryRequest pageQueryRequest);

    /**
     * @param id
     * @return
     */
    ProjectHistory findProjectHistoryById(String id);

    //#################################
    // profile
    //#################################

    /**
     * 设置折算比例
     *
     * @param id
     * @param reducedPercent
     */
    void setProjectReducedPercent(String id, BigDecimal reducedPercent);

    //#################################
    //manager
    //#################################

    /**
     * 查看项目管理员
     *
     * @param projectId
     * @return
     */
    List<User> listProjectManagers(String projectId);

    /**
     * 判断用户是否是管理员
     *
     * @param projectId
     * @param user
     * @return
     */
    boolean isProjectManager(String projectId, User user);

    /**
     * 设置项目管理员
     *
     * @param projectId
     * @param userIds
     */
    void setProjectManagers(String projectId, String[] userIds);

    /**
     * @param id
     * @param user
     * @return
     */
    List<ProjectManageRelationship> listProjectManageRelationships(String id, User user);

    /**
     * @param request
     * @param user
     * @return
     */
    ProjectManageRelationship createProjectManageRelationship(SaveProjectManageRelationshipRequest request, User user);

    /**
     * @param id
     * @param request
     * @param user
     * @return
     */
    ProjectManageRelationship updateProjectManageRelationship(String id, SaveProjectManageRelationshipRequest request, User user);

    /**
     *
     * @param id
     * @param user
     */
    void deleteProjectManageRelationship(String id, User user);
}
