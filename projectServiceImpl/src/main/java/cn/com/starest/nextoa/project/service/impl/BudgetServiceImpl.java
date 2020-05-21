package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentBudget;
import cn.com.starest.nextoa.project.repository.BizDepartmentBudgetRepository;
import cn.com.starest.nextoa.project.repository.BizDepartmentRepository;
import cn.com.starest.nextoa.project.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author dz
 */
@Service
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	private BizDepartmentRepository bizDepartmentRepository;

	@Autowired
	private BizDepartmentBudgetRepository bizDepartmentBudgetRepository;

	@Override
	public void updateBizDepartmentBudget(String id, int year, BigDecimal budget) {
		BizDepartment bizDepartment = bizDepartmentRepository.findById(id);
		if (bizDepartment == null) {
			return;
		}

		BizDepartmentBudget bizDepartmentBudget = bizDepartmentBudgetRepository.findFirstByBizDepartmentAndYear(
				bizDepartment,
				year);
		if (bizDepartmentBudget == null) {
			bizDepartmentBudget = new BizDepartmentBudget();
			bizDepartmentBudget.setBizDepartment(bizDepartment);
			bizDepartmentBudget.setYear(year);
			bizDepartmentBudget.setBudget(BigDecimal.ZERO);
			bizDepartmentBudget = bizDepartmentBudgetRepository.save(bizDepartmentBudget);
		}

		bizDepartmentBudget.setBudget(budget);
		bizDepartmentBudgetRepository.save(bizDepartmentBudget);
	}

	@Override
	public BigDecimal getBizDepartmentBudget(BizDepartment bizDepartment, int year) {
		BizDepartmentBudget bizDepartmentBudget = bizDepartmentBudgetRepository.findFirstByBizDepartmentAndYear(
				bizDepartment,
				year);
		if (bizDepartmentBudget == null) {
			return BigDecimal.ZERO;
		}

		return bizDepartmentBudget.getBudget();
	}

}
