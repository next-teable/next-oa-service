package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentBudgetSummary;
import cn.com.starest.nextoa.project.web.dto.SaveBizDepartmentBudgetParameter;
import cn.com.starest.nextoa.project.web.support.BudgetRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("预算管理")
@RestController
@RequestMapping("/api/budgets")
public class BudgetRestController {

	@Autowired
	private BudgetRestSupport budgetRestSupport;

	@ApiOperation(value = "修改部门预算")
	@RequestMapping(value = "/bizDepartments/{id}", method = RequestMethod.POST)
	public void updateBizDepartmentBudget(@PathVariable String id,
										  @Validated @RequestBody SaveBizDepartmentBudgetParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		budgetRestSupport.updateBizDepartmentBudget(id, request, user);
	}

	@ApiOperation(value = "查询部门预算详情")
	@RequestMapping(value = {"/bizDepartments/{id}", "/shared/bizDepartments/{id}"}, method = RequestMethod.GET)
	public BizDepartmentBudgetSummary findBizDepartmentBudgetById(@PathVariable String id) {
		return budgetRestSupport.findBizDepartmentBudgetById(id);
	}

	@ApiOperation(value = "查询部门预算列表")
	@RequestMapping(value = {"/bizDepartments", "/shared/bizDepartments"}, method = RequestMethod.GET)
	public List<BizDepartmentBudgetSummary> listBizDepartmentBudgets() {
		return budgetRestSupport.listBizDepartmentBudgets();
	}

}
