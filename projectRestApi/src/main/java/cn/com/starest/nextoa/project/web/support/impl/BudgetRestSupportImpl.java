package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.exception.BizException;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.service.BizDepartmentService;
import cn.com.starest.nextoa.project.service.BudgetService;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentBudgetSummary;
import cn.com.starest.nextoa.project.web.dto.SaveBizDepartmentBudgetParameter;
import cn.com.starest.nextoa.project.web.support.BudgetRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class BudgetRestSupportImpl implements BudgetRestSupport {

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private BizDepartmentService bizDepartmentService;

	@Override
	public void updateBizDepartmentBudget(String id, SaveBizDepartmentBudgetParameter request, User user) {
		budgetService.updateBizDepartmentBudget(id, 2016, request.getBudget2016());
		budgetService.updateBizDepartmentBudget(id, 2017, request.getBudget2017());
		budgetService.updateBizDepartmentBudget(id, 2018, request.getBudget2018());
		budgetService.updateBizDepartmentBudget(id, 2019, request.getBudget2019());
		budgetService.updateBizDepartmentBudget(id, 2020, request.getBudget2020());
	}

	@Override
	public BizDepartmentBudgetSummary findBizDepartmentBudgetById(String id) {
		BizDepartment bizDepartment = bizDepartmentService.findBizDepartmentById(id);
		if (bizDepartment == null) {
			throw new BizException("无效的部门id");
		}

		BizDepartmentBudgetSummary result = new BizDepartmentBudgetSummary();
		result.setBizDepartmentId(bizDepartment.getId());
		result.setBizDepartmentName(bizDepartment.getName());
		result.setBudget2016(budgetService.getBizDepartmentBudget(bizDepartment, 2016));
		result.setBudget2017(budgetService.getBizDepartmentBudget(bizDepartment, 2017));
		result.setBudget2018(budgetService.getBizDepartmentBudget(bizDepartment, 2018));
		result.setBudget2019(budgetService.getBizDepartmentBudget(bizDepartment, 2019));
		result.setBudget2020(budgetService.getBizDepartmentBudget(bizDepartment, 2020));
		return result;
	}

	@Override
	public List<BizDepartmentBudgetSummary> listBizDepartmentBudgets() {
		PageQueryParameter queryParameter = new PageQueryParameter(0, 50);
		return bizDepartmentService.listBizDepartments(queryParameter).getContent().stream().map(bizDepartment -> {
			BizDepartmentBudgetSummary result = new BizDepartmentBudgetSummary();
			result.setBizDepartmentId(bizDepartment.getId());
			result.setBizDepartmentName(bizDepartment.getName());
			result.setBudget2016(budgetService.getBizDepartmentBudget(bizDepartment, 2016));
			result.setBudget2017(budgetService.getBizDepartmentBudget(bizDepartment, 2017));
			result.setBudget2018(budgetService.getBizDepartmentBudget(bizDepartment, 2018));
			result.setBudget2019(budgetService.getBizDepartmentBudget(bizDepartment, 2019));
			result.setBudget2020(budgetService.getBizDepartmentBudget(bizDepartment, 2020));
			return result;
		}).collect(Collectors.toList());
	}

}
