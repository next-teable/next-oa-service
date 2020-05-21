package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SaveProjectReceivedPaymentParameter;
import cn.com.starest.nextoa.project.web.support.ProjectReceivedPaymentRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("项目回款")
@RestController
@RequestMapping("/api")
public class ProjectReceivedPaymentRestController {

	@Autowired
	private ProjectReceivedPaymentRestSupport projectReceivedPaymentRestSupport;

	@ApiOperation(value = "创建项目回款")
	@RequestMapping(value = "/projectReceivedPayments", method = RequestMethod.POST)
	public ProjectReceivedPaymentSummary createProjectReceivedPayment(@Validated @RequestBody SaveProjectReceivedPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectReceivedPaymentRestSupport.createProjectReceivedPayment(request, user);
	}

	@ApiOperation(value = "修改项目回款")
	@RequestMapping(value = "/projectReceivedPayments/{id}", method = RequestMethod.POST)
	public ProjectReceivedPaymentSummary updateProjectReceivedPayment(@PathVariable String id,
																	  @Validated @RequestBody SaveProjectReceivedPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectReceivedPaymentRestSupport.updateProjectReceivedPayment(id, request, user);
	}

	@ApiOperation(value = "查看项目回款")
	@RequestMapping(value = "/projectReceivedPayments/{id}", method = RequestMethod.GET)
	public ProjectReceivedPaymentDetail findProjectReceivedPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectReceivedPaymentRestSupport.findProjectReceivedPaymentById(id, user);
	}

	@ApiOperation(value = "删除项目回款")
	@RequestMapping(value = "/projectReceivedPayments/{id}", method = RequestMethod.DELETE)
	public void deleteProjectReceivedPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		projectReceivedPaymentRestSupport.deleteProjectReceivedPaymentById(id, user);
	}

	@ApiOperation(value = "查看项目回款列表")
	@RequestMapping(value = "/projectReceivedPayments", method = RequestMethod.GET)
	public Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(ProjectReceivedPaymentQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectReceivedPaymentRestSupport.listProjectReceivedPayments(request, user);
	}

}
