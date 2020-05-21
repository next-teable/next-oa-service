package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentBudget;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface BizDepartmentBudgetRepository extends AbstractRepository<BizDepartmentBudget> {

	List<BizDepartmentBudget> findListByBizDepartmentOrderByYearAsc(BizDepartment bizDepartment);

	BizDepartmentBudget findFirstByBizDepartmentAndYear(BizDepartment bizDepartment, int year);

}