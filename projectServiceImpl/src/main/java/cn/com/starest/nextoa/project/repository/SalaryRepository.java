package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.SalaryRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Salary Repository
 */
public interface SalaryRepository extends AbstractRepository<Salary>, SalaryRepositoryCustom {

	/**
	 * @param year
	 * @param month
	 * @param company
	 * @param project
	 * @param employee
	 * @return
	 */
	Salary findFirstByYearAndMonthAndCompanyAndProjectAndEmployee(int year,
																  int month,
																  Company company,
																  Project project,
																  String employee);

	/**
	 * @param year
	 * @param month
	 * @param company
	 * @param bizDepartment
	 * @param employee
	 * @return
	 */
	Salary findFirstByYearAndMonthAndCompanyAndBizDepartmentAndEmployee(int year,
																		int month,
																		Company company,
																		BizDepartment bizDepartment,
																		String employee);

	/**
	 * @param salaryImportHistory
	 * @return
	 */
	List<Salary> findListBySalaryImportHistory(SalaryImportHistory salaryImportHistory);
}