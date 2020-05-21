package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("项目管理")
@RestController
@RequestMapping("/api")
public class ProjectRestController {

    @Autowired
    private ProjectRestSupport projectRestSupport;

    @ApiOperation(value = "创建项目")
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ProjectSummary createProject(@Validated @RequestBody SaveProjectParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.createProject(request, user);
    }

    @ApiOperation(value = "修改项目-只能修改未发布的项目")
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.POST)
    public ProjectSummary updateProject(@PathVariable String id, @Validated @RequestBody SaveProjectParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.updateProject(id, request, user);
    }

    @ApiOperation(value = "发布项目-发布后的项目才生效")
    @RequestMapping(value = "/projects/{id}/publish", method = RequestMethod.POST)
    public void publishProject(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        projectRestSupport.publishProject(id, user);
    }

    @ApiOperation(value = "结束项目-发布后的项目才可以结束")
    @RequestMapping(value = "/projects/{id}/close", method = RequestMethod.POST)
    public void closeProject(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        projectRestSupport.closeProject(id, user);
    }

    @ApiOperation(value = "删除项目-只能删除未发布的项目")
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
    public void deleteProjectById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        projectRestSupport.deleteProjectById(id, user);
    }

    @ApiOperation(value = "查看项目")
    @RequestMapping(value = {"/projects/{id}"}, method = RequestMethod.GET)
    public ProjectDetail findProjectById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.findProjectById(id, user);
    }

    @ApiOperation(value = "查看项目列表")
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Page<ProjectSummary> listProjects(ProjectQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.listProjects(request, user);
    }

    @ApiOperation(value = "查看项目修改历史详情")
    @RequestMapping(value = "/projects/histories/{id}", method = RequestMethod.GET)
    public ProjectHistorySummary findProjectHistoryById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.findProjectHistoryById(id, user);
    }

    @ApiOperation(value = "查看项目修改历史列表")
    @RequestMapping(value = "/projects/{id}/history", method = RequestMethod.GET)
    public Page<ProjectHistorySummary> listProjectHistories(@PathVariable String id, PageQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.listProjectHistories(id, request, user);
    }

    @ApiOperation(value = "查看项目所属管理关系列表")
    @RequestMapping(value = "/projects/{id}/manageRelationships", method = RequestMethod.GET)
    public List<ProjectManageRelationshipSummary> listProjectManageRelationships(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.listProjectManageRelationships(id, user);
    }

    @ApiOperation(value = "创建项目所属管理关系")
    @RequestMapping(value = "/projects/manageRelationships", method = RequestMethod.POST)
    public ProjectManageRelationshipSummary createProjectManageRelationships(@Validated @RequestBody SaveProjectManageRelationshipParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.createProjectManageRelationship(request, user);
    }

    @ApiOperation(value = "更新项目所属管理关系")
    @RequestMapping(value = "/projects/manageRelationships/{id}", method = RequestMethod.POST)
    public ProjectManageRelationshipSummary createProjectManageRelationships(@PathVariable String id, @Validated @RequestBody SaveProjectManageRelationshipParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.updateProjectManageRelationship(id, request, user);
    }

    @ApiOperation(value = "删除项目所属管理关系")
    @RequestMapping(value = "/projects/manageRelationships/{id}", method = RequestMethod.DELETE)
    public void deleteProjectManageRelationshipById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        projectRestSupport.deleteProjectManageRelationship(id, user);
    }
}
