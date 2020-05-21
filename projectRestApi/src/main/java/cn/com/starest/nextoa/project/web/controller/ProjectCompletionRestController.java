package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectCompletionSummary;
import cn.com.starest.nextoa.project.web.dto.SaveProjectCompletionParameter;
import cn.com.starest.nextoa.project.web.support.ProjectCompletionRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("竣工管理")
@RestController
@RequestMapping("/api")
public class ProjectCompletionRestController {

	@Autowired
	private ProjectCompletionRestSupport projectCompletionRestSupport;

	@ApiOperation(value = "创建竣工管理")
	@RequestMapping(value = "/projectCompletions", method = RequestMethod.POST)
	public ProjectCompletionSummary createProjectCompletion(@Validated @RequestBody SaveProjectCompletionParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectCompletionRestSupport.createProjectCompletion(request, user);
	}

	@ApiOperation(value = "修改竣工管理")
	@RequestMapping(value = "/projectCompletions/{id}", method = RequestMethod.POST)
	public ProjectCompletionSummary updateProjectCompletion(@PathVariable String id,
															@Validated @RequestBody SaveProjectCompletionParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectCompletionRestSupport.updateProjectCompletion(id, request, user);
	}

	@ApiOperation(value = "查看竣工管理")
	@RequestMapping(value = "/projectCompletions/{id}", method = RequestMethod.GET)
	public ProjectCompletionDetail findProjectCompletionById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectCompletionRestSupport.findProjectCompletionById(id, user);
	}

	@ApiOperation(value = "删除竣工管理")
	@RequestMapping(value = "/projectCompletions/{id}", method = RequestMethod.DELETE)
	public void deleteProjectCompletionById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		projectCompletionRestSupport.deleteProjectCompletionById(id, user);
	}

	@ApiOperation(value = "查看竣工管理列表")
	@RequestMapping(value = "/projectCompletions", method = RequestMethod.GET)
	public Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectCompletionRestSupport.listProjectCompletions(request, user);
	}

}
