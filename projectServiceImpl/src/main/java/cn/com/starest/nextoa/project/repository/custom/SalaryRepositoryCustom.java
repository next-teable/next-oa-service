package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.Salary;
import cn.com.starest.nextoa.project.domain.request.SalaryQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SalaryRepositoryCustom {

	Page<Salary> queryPage(SalaryQueryRequest request);

	/**
	 * 按年统计公司工资支出
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalPay(Company company, int year);

	/**
	 * 按月统计公司工资支出
	 *
	 * @param company
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateTotalPay(Company company, int year, int month);

	/**
	 * 按月统计部门工资支出
	 *
	 * @param bizDepartment
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateTotalPay(BizDepartment bizDepartment, int year, int month);

	/**
	 * 按月统计项目工资支出
	 *
	 * @param project
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateTotalPay(Project project, int year, int month);

	/**
	 * 按年统计公司工资中支出到项目的部分
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateProjectTotalPay(Company company, int year);

	/**
	 * 统计项目支出的总工资
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateProjectTotalPay(Project project);

	/**
	 * 按年统计项目支出的总工资
	 *
	 * @param project
	 * @param year
	 * @return
	 */
	BigDecimal calculateProjectTotalPay(Project project, int year);

	/**
	 * 按年统计公司工资中支出到部门的部分
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateBizDepartmentTotalPay(Company company, int year);

	/**
	 * 按年统计部门支出的总工资
	 *
	 * @param bizDepartment
	 * @param year
	 * @return
	 */
	BigDecimal calculateBizDepartmentTotalPay(BizDepartment bizDepartment, int year);

}
