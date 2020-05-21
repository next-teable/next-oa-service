package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface CompanyCapitalQueryRequest extends PageQueryRequest {



	String getCompanyId();

	Integer getYear();

}
