package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectSettlementRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("项目结算")
@RestController
@RequestMapping("/api")
public class ProjectSettlementRestController {

	@Autowired
	private ProjectSettlementRestSupport projectSettlementRestSupport;

	@ApiOperation(value = "竣工列表")
	@RequestMapping(value = "/projectSettlements/projectCompletions", method = RequestMethod.GET)
	public Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.listProjectCompletions(request, user);
	}

	@ApiOperation(value = "查看竣工管理")
	@RequestMapping(value = "/projectSettlements/projectCompletions/{id}", method = RequestMethod.GET)
	public ProjectCompletionDetail findProjectCompletionById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.findProjectCompletionById(id, user);
	}

	@ApiOperation(value = "准备项目结算")
	@RequestMapping(value = "/projectSettlements/prepare/projectCompletions/{id}", method = RequestMethod.GET)
	public ProjectSettlementSummary prepareProjectSettlement(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.prepareProjectSettlement(id, user);
	}

	@ApiOperation(value = "新增项目结算")
	@RequestMapping(value = "/projectSettlements/projectCompletions/{id}", method = RequestMethod.POST)
	public ProjectSettlementSummary createProjectSettlement(@PathVariable String id,
															@Validated @RequestBody SaveProjectSettlementParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.createProjectSettlement(id, request, user);
	}

	@ApiOperation(value = "修改项目结算")
	@RequestMapping(value = "/projectSettlements/{id}", method = RequestMethod.POST)
	public ProjectSettlementSummary updateProjectSettlement(@PathVariable String id,
															@Validated @RequestBody SaveProjectSettlementParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.updateProjectSettlement(id, request, user);
	}

	@ApiOperation(value = "进行项目结算,要求所有的提成剩余金额为0")
	@RequestMapping(value = "/projectSettlements/{id}/do", method = RequestMethod.POST)
	public void doProjectSettlement(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		projectSettlementRestSupport.doProjectSettlement(id, user);
	}

	@ApiOperation(value = "查看项目结算")
	@RequestMapping(value = "/projectSettlements/{id}", method = RequestMethod.GET)
	public ProjectSettlementSummary findProjectSettlementById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.findProjectSettlementById(id, user);
	}

	@ApiOperation(value = "删除项目结算")
	@RequestMapping(value = "/projectSettlements/{id}", method = RequestMethod.DELETE)
	public void deleteProjectSettlementById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		projectSettlementRestSupport.deleteProjectSettlementById(id, user);
	}

	@ApiOperation(value = "查看项目结算报销记录")
	@RequestMapping(value = "/projectSettlements/{id}/reimburses/{userId}", method = RequestMethod.GET)
	public List<PaymentSummary> getProjectSettlementReimburseList(@PathVariable String id,
																  @PathVariable String userId) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.getProjectSettlementPaymentList(id, userId, user);
	}

	@ApiOperation(value = "查看项目结算列表")
	@RequestMapping(value = "/projectSettlements", method = RequestMethod.GET)
	public Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectSettlementRestSupport.listProjectSettlements(request, user);
	}

}
