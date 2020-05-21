package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface SalaryQueryRequest extends PageQueryRequest {

	/**
	 * @return 公司id
	 */
	String getCompanyId();

	/**
	 * @return 项目id
	 */
	String getProjectId();

	/**
	 * @return 部门id
	 */
	String getBizDepartmentId();

	/**
	 * @return 员工name
	 */
	String getEmployee();

	/**
	 * @return 年份
	 */
	Integer getYear();

	/**
	 * @return 月份
	 */
	Integer getMonth();

}
