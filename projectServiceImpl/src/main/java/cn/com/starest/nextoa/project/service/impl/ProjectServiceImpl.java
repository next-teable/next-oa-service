package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectManageRelationshipRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectRequest;
import cn.com.starest.nextoa.project.domain.rule.ProjectReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectException;
import cn.com.starest.nextoa.project.exception.ProjectNotFoundException;
import cn.com.starest.nextoa.project.repository.BizDepartmentRepository;
import cn.com.starest.nextoa.project.repository.ProjectHistoryRepository;
import cn.com.starest.nextoa.project.repository.ProjectManageRelationshipRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.project.service.DictionaryService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ProjectService;
import cn.com.starest.nextoa.service.AccountService;
import cn.com.starest.nextoa.service.SystemSettingService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectHistoryRepository projectHistoryRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private List<ProjectReference> projectReferenceList;

    @Autowired
    private ModulePermissionService modulePermissionService;

    @Autowired
    private ProjectManageRelationshipRepository projectManageRelationshipRepository;

    @Autowired
    private BizDepartmentRepository bizDepartmentRepository;

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
        return modulePermissionService.listGrantedActions(Module.PROJECT, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(Project project, User byWho) {
        ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.PROJECT, byWho)
                .toArray(ModuleActions.EMPTY_ACTIONS);
        if (Project.isDraft(project)) {
            List<ModuleActions.ModuleAction> result = Lists.newArrayList(grantedActions);
            result.removeIf(item -> item == ModuleActions.CLOSE_PROJECT_ACTION);
            return result.toArray(ModuleActions.EMPTY_ACTIONS);
        } else if (Project.isRunning(project)) {
            List<ModuleActions.ModuleAction> result = Lists.newArrayList(grantedActions);
            result.removeIf(item -> item == ModuleActions.DELETE_ACTION);
            result.removeIf(item -> item == ModuleActions.PUBLISH_ACTION);
            return result.toArray(ModuleActions.EMPTY_ACTIONS);
        } else if (Project.isClosed(project)) {
            //			List<ModuleActions.ModuleAction> result = Lists.newArrayList(grantedActions);
            //			result.removeIf(item -> item == ModuleActions.DELETE_ACTION);
            //			result.removeIf(item -> item == ModuleActions.PUBLISH_ACTION);
            //			result.removeIf(item -> item == ModuleActions.EDIT_ACTION);
            //			result.removeIf(item -> item == ModuleActions.CLOSE_PROJECT_ACTION);
            //			return result.toArray(ModuleActions.EMPTY_ACTIONS);
            return ModuleActions.EMPTY_ACTIONS;
        }
        return ModuleActions.EMPTY_ACTIONS;
    }

    @Override
    public Project createProject(SaveProjectRequest request, User byWho) {
        checkDuplicateProject(request, null);

        Project project = new Project();
        BeanUtils.copyProperties(request, project);
        handleProjectReference(request, project);
        Project.onCreate(project, byWho);

        return projectRepository.save(project);
    }

    private void handleProjectReference(SaveProjectRequest request, Project project) {
        project.setCompany(baseDataService.findCompanyById(request.getCompanyId()));

        if (!StringUtils.isEmpty(request.getFrameworkContractId())) {
            project.setFrameworkContract(baseDataService.findFrameworkContractById(request.getFrameworkContractId()));
        } else {
            project.setFrameworkContract(null);
        }

        project.setProjectType(dictionaryService.findProjectTypeById(request.getProjectTypeId()));

        project.setDeliveryType(dictionaryService.findProjectDeliveryTypeById(request.getDeliveryTypeId()));

        project.setFirstParty(baseDataService.findFirstPartyById(request.getFirstPartyId()));

        project.setManagers(Arrays.asList(request.getManagerIds())
                .stream()
                .map(userId -> accountService.findById(userId))
                .collect(Collectors.toList()));
    }

    @Override
    public Project updateProject(String id, SaveProjectRequest request, User byWho) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        if (Project.isCompleted(project)) {
            throw new ProjectException("该项目已竣工,不能直接修改项目信息");
        }

        if (Project.isSettled(project)) {
            throw new ProjectException("该项目已结算,不能直接修改项目信息");
        }

        if (Project.isClosed(project)) {
            throw new ProjectException("该项目已结项,不能直接修改项目信息");
        }

        checkDuplicateProject(request, project);

        if (project.getStatus() == ProjectStatus.RUNNING) {
            BeanUtils.copyProperties(request, project);
            handleProjectReference(request, project);
            Project.onModify(project, byWho);

            ProjectHistory projectHistory = new ProjectHistory();
            BeanUtils.copyProperties(project, projectHistory, "id", "sort");
            projectHistory.setProject(project);
            ProjectHistory.onModify(projectHistory, byWho);

            projectHistoryRepository.save(projectHistory);
            return projectRepository.save(project);
        } else {
            BeanUtils.copyProperties(request, project);
            handleProjectReference(request, project);
            Project.onModify(project, byWho);

            return projectRepository.save(project);
        }
    }

    public Project updateProjectWithHistory(String id, SaveProjectRequest request, User byWho) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        if (Project.isCompleted(project)) {
            throw new ProjectException("该项目已竣工,不能直接修改项目信息");
        }

        if (Project.isSettled(project)) {
            throw new ProjectException("该项目已结算,不能直接修改项目信息");
        }

        if (Project.isClosed(project)) {
            throw new ProjectException("该项目已结项,不能直接修改项目信息");
        }

        checkDuplicateProject(request, project);

        BeanUtils.copyProperties(request, project);
        project.setProjectType(dictionaryService.findProjectTypeById(request.getProjectTypeId()));
        project.setDeliveryType(dictionaryService.findProjectDeliveryTypeById(request.getDeliveryTypeId()));
        project.setFirstParty(baseDataService.findFirstPartyById(request.getFirstPartyId()));
        Project.onModify(project, byWho);

        ProjectHistory projectHistory = new ProjectHistory();
        BeanUtils.copyProperties(project, projectHistory, "id", "sort");
        ProjectHistory.onModify(projectHistory, byWho);

        projectHistoryRepository.save(projectHistory);
        return projectRepository.save(project);
    }

    @Override
    public Project publishProject(String id, User byWho) {
        Project existedProject = projectRepository.findById(id);
        if (existedProject == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        if (!Project.isDraft(existedProject)) {
            throw new ProjectException("该项目已生效,请勿重复操作");
        }

        if (existedProject.getProjectType() == null) {
            throw new ProjectException("请指定项目类型");
        }

        if (existedProject.getProjectType() == null) {
            throw new ProjectException("请指定甲方");
        }

        if (existedProject.getProjectType() == null) {
            throw new ProjectException("请指定交付方式");
        }

        if (existedProject.getManagers() == null || existedProject.getManagers().isEmpty()) {
            throw new ProjectException("请指定项目经理");
        }

        existedProject.setStatus(ProjectStatus.RUNNING);
        existedProject.setStartAt(new Date());
        existedProject.setStartBy(byWho);

        ProjectHistory projectHistory = new ProjectHistory();
        BeanUtils.copyProperties(existedProject, projectHistory, "id");
        ProjectHistory.onModify(projectHistory, byWho);
        projectHistoryRepository.save(projectHistory);

        return projectRepository.save(existedProject);
    }

    @Override
    public Project closeProject(String projectId, User byWho) {
        Project existedProject = projectRepository.findById(projectId);
        if (existedProject == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        if (Project.isDraft(existedProject)) {
            throw new ProjectException("该项目未发布,不能进行结项操作");
        }

        if (Project.isClosed(existedProject)) {
            throw new ProjectException("该项目已结项,请勿重复操作");
        }

        if (!Project.isSettled(existedProject)) {
            throw new ProjectException("该项目未结算,不能进行结项操作");
        }

        existedProject.setStatus(ProjectStatus.CLOSED);
        existedProject.setCloseAt(new Date());
        existedProject.setCloseBy(byWho);

        ProjectHistory projectHistory = new ProjectHistory();
        BeanUtils.copyProperties(existedProject, projectHistory, "id");
        ProjectHistory.onModify(projectHistory, byWho);
        projectHistoryRepository.save(projectHistory);

        return projectRepository.save(existedProject);
    }

    @Override
    public void deleteProjectById(String id, User byWho) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        if (!Project.isDraft(project)) {
            throw new ProjectException("该项目已生效,不能删除");
        }

        if (projectReferenceList != null) {
            projectReferenceList.forEach(ref -> {
                if (ref.hasReference(project)) {
                    throw new ProjectException("该项目已被其他业务数据引用,不能删除");
                }
            });
        }

        projectHistoryRepository.deleteByProject(project);
        projectRepository.delete(project);
    }

    @Override
    public Project findProjectById(String id, User byWho) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return projectRepository.findById(id);
    }

    @Override
    public Page<Project> listProjects(ProjectQueryRequest request, User byWho) {
        return projectRepository.queryPage(request);
    }

    @Override
    public Page<Project> listManagedProjects(ProjectQueryRequest request, User byWho) {
        return projectRepository.queryPageByManager(request, byWho);
    }

    @Override
    public Page<Project> listSupervisedProjects(ProjectQueryRequest request, User byWho) {
        //if the user is global supervisors, the user can query all the projects
        boolean supervisor = User.ADMINISTRATOR.equals(byWho.getUsername());
        if (!supervisor) {
            supervisor = systemSettingService.isProjectSupervisor(byWho);
        }

        if (supervisor) {
            return projectRepository.queryPage4SupervisedMonitorProject(request);
        }

        //else only the project's manager can query the project
        return projectRepository.queryPage4ManagedMonitorProject(request, byWho);
    }

    @Override
    public Page<ProjectManageRelationship> listSupervisedProjectManageRelationship(ProjectQueryRequest request, User byWho) {
        //if the user is global supervisors, the user can query all the projects
        boolean supervisor = User.ADMINISTRATOR.equals(byWho.getUsername());
        if (!supervisor) {
            supervisor = systemSettingService.isProjectSupervisor(byWho);
        }

        if (supervisor) {
            return projectManageRelationshipRepository.queryPage4SupervisedMonitorProjectManageRelationship(request);
        }

        //else only the project's manager can query the project
        return projectManageRelationshipRepository.queryPage4SupervisedMonitorProjectManageRelationship(request, byWho);
    }

    @Override
    public List<ProjectManageRelationship> listSupervisedProjectManageRelationship(String bizDepartmentId) {
        BizDepartment bizDepartment = bizDepartmentRepository.findById(bizDepartmentId);
        if (null == bizDepartment) {
            throw new EntityNotFoundException("没有找到对应的业务部门");
        }
        return projectManageRelationshipRepository.findByBelongTo(bizDepartment);
    }

    @Override
    public Page<ProjectHistory> listProjectHistories(String projectId, PageQueryRequest pageQueryRequest) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        return projectHistoryRepository.findPageByProject(project,
                new PageRequest(pageQueryRequest.getStart(),
                        pageQueryRequest.getLimit(),
                        new Sort(Sort.Direction.DESC, "modifiedAt")));
    }

    @Override
    public ProjectHistory findProjectHistoryById(String id) {
        return projectHistoryRepository.findById(id);
    }

    //#################################
    // profile
    //#################################

    @Override
    public void setProjectReducedPercent(String id, BigDecimal reducedPercent) {
        projectRepository.setReducedPercent(id, reducedPercent);
    }

    //#################################
    //manager
    //#################################

    @Override
    public List<User> listProjectManagers(String projectId) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }
        return project.getManagers();
    }

    @Override
    public boolean isProjectManager(String projectId, User user) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }
        if (project.getManagers() != null) {
            for (User item : project.getManagers()) {
                if (user.getId().equals(item.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setProjectManagers(String projectId, String[] userIds) {
        projectRepository.setProjectManagers(projectId,
                Arrays.asList(userIds)
                        .stream()
                        .map(userId -> accountService.findById(userId))
                        .collect(Collectors.toList()));
    }

    @Override
    public List<ProjectManageRelationship> listProjectManageRelationships(String id, User user) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }
        return projectManageRelationshipRepository.findByProject(project);
    }

    @Override
    public ProjectManageRelationship createProjectManageRelationship(SaveProjectManageRelationshipRequest request, User user) {
        if (null == request.getYear()) {
            throw new IllegalArgumentException("年份不能为空");
        }

        Project project = projectRepository.findById(request.getProjectId());
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        BizDepartment bizDepartment = bizDepartmentRepository.findById(request.getBizDepartmentId());
        if (null == bizDepartment) {
            throw new IllegalArgumentException("没有找到对应的业务部门");
        }

        if (bizDepartment.getType() != BizDepartmentType.BUSINESS) {
            throw new IllegalArgumentException("部门不是事业部");
        }

        if (null != projectManageRelationshipRepository.findByProjectAndYear(project, request.getYear())) {
            throw new IllegalArgumentException("该项目已存在相同年的所属关系");
        }

        ProjectManageRelationship relationship = new ProjectManageRelationship();
        relationship.setYear(request.getYear());
        relationship.setBelongTo(bizDepartment);
        relationship.setProject(project);
        relationship.setManagers(Arrays.asList(request.getManagerIds())
                .stream()
                .map(userId -> accountService.findById(userId))
                .collect(Collectors.toList()));
        projectManageRelationshipRepository.save(relationship);
        return relationship;
    }

    @Override
    public ProjectManageRelationship updateProjectManageRelationship(String id, SaveProjectManageRelationshipRequest request, User user) {
        if (null == request.getYear()) {
            throw new IllegalArgumentException("年份不能为空");
        }

        ProjectManageRelationship projectManageRelationship = projectManageRelationshipRepository.findById(id);
        if (projectManageRelationship == null) {
            throw new IllegalArgumentException("没有找到对应的项目所属关系");
        }

        BizDepartment bizDepartment = bizDepartmentRepository.findById(request.getBizDepartmentId());
        if (null == bizDepartment) {
            throw new IllegalArgumentException("没有找到对应的业务部门");
        }

        if (bizDepartment.getType() != BizDepartmentType.BUSINESS) {
            throw new IllegalArgumentException("部门不是事业部");
        }

        ProjectManageRelationship existed = projectManageRelationshipRepository.findByProjectAndYear(projectManageRelationship.getProject(), request.getYear());

        if (null != existed && !existed.getId().equals(id)) {
            throw new IllegalArgumentException("该项目已存在相同年的所属关系");
        }


        projectManageRelationship.setManagers(Arrays.asList(request.getManagerIds())
                .stream()
                .map(userId -> accountService.findById(userId))
                .collect(Collectors.toList()));
        projectManageRelationship.setBelongTo(bizDepartment);
        projectManageRelationship.setYear(request.getYear());
        projectManageRelationshipRepository.save(projectManageRelationship);

        return projectManageRelationship;
    }

    @Override
    public void deleteProjectManageRelationship(String id, User user) {
        ProjectManageRelationship projectManageRelationship = projectManageRelationshipRepository.findById(id);
        if (projectManageRelationship == null) {
            throw new IllegalArgumentException("没有找到对应的项目所属关系");
        }
        projectManageRelationshipRepository.delete(projectManageRelationship);
    }

    private void checkDuplicateProject(SaveProjectRequest request, Project existedProject) {
        if (existedProject == null) {
            if (null != projectRepository.findFirstByName(request.getName())) {
                throw new ProjectException("项目名不能重复");
            }
            if (null != projectRepository.findFirstByCode(request.getCode())) {
                throw new ProjectException("项目编码不能重复");
            }
        } else {
            Project matchedProject = projectRepository.findFirstByName(request.getName());
            if (null != matchedProject && !matchedProject.getId().equals(existedProject.getId())) {
                throw new ProjectException("项目名不能重复");
            }

            matchedProject = projectRepository.findFirstByCode(request.getCode());
            if (null != matchedProject && !matchedProject.getId().equals(existedProject.getId())) {
                throw new ProjectException("项目编码不能重复");
            }
        }
    }

}
