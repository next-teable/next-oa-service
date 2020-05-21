package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface SalaryAggregationQueryRequest extends PageQueryRequest {

	//公司
	String getCompanyId();

	//年份
	Integer getYear();

	//月份
	Integer getMonth();

}
