package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.CompanyCapitalQueryRequest;

/**
 * @author dz
 */
public class CompanyCapitalQueryParameter extends PageQueryParameter implements CompanyCapitalQueryRequest {


	String companyId;

	Integer year;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
