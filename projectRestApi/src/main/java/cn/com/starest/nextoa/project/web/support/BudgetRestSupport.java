package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentBudgetSummary;
import cn.com.starest.nextoa.project.web.dto.SaveBizDepartmentBudgetParameter;

import java.util.List;

/**
 * 预算管理
 *
 * @author dz
 */
public interface BudgetRestSupport {

	void updateBizDepartmentBudget(String id, SaveBizDepartmentBudgetParameter request, User user);

	BizDepartmentBudgetSummary findBizDepartmentBudgetById(String id);

	List<BizDepartmentBudgetSummary> listBizDepartmentBudgets();

}
