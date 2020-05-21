package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.support.ProjectProfitRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("项目利润分配")
@RestController
@RequestMapping("/api")
public class ProjectProfitRestController {

	@Autowired
	private ProjectProfitRestSupport projectProfitRestSupport;

	@ApiOperation(value = "查看项目利润分配")
	@RequestMapping(value = "/projectProfits/{id}", method = RequestMethod.GET)
	public ProjectSettlementSummary findProjectSettlementById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return projectProfitRestSupport.findProjectSettlementById(id, user);
	}

	@ApiOperation(value = "查看项目利润分配列表")
	@RequestMapping(value = "/projectProfits", method = RequestMethod.GET)
	public Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return projectProfitRestSupport.listProjectSettlements(request, user);
	}

	@ApiOperation(value = "查看项目利润分配报销记录")
	@RequestMapping(value = "/projectProfits/{id}/reimburses/{userId}", method = RequestMethod.GET)
	public List<PaymentSummary> getProjectSettlementReimburseList(@PathVariable String id,
																  @PathVariable String userId) {
		User user = SecurityContexts.getContext().requireUser();
		return projectProfitRestSupport.getProjectSettlementPaymentList(id, userId, user);
	}

}
