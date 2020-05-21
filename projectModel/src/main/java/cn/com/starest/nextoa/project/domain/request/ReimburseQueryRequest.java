package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface ReimburseQueryRequest extends PageQueryRequest, ReimburseContext {

	/**
	 * @return 项目id
	 */
	String getProjectId();

	/**
	 * @return 部门id
	 */
	String getBizDepartmentId();

	/**
	 * @return 是否结算
	 */
	Boolean getSettled();

}
