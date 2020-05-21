package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface BudgetService {

	void updateBizDepartmentBudget(String id, int year, BigDecimal budget);

	BigDecimal getBizDepartmentBudget(BizDepartment bizDepartment, int year);

}
