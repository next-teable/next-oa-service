package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SalaryAggregationQueryRequest;

/**
 * @author dz
 */
public class SalaryAggregationQueryParameter extends PageQueryParameter implements SalaryAggregationQueryRequest {

	String companyId;

	Integer year;

	Integer month;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
}
