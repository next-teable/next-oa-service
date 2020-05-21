package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface OrderQueryRequest extends PageQueryRequest {

	String getName();

	String getCode();

	String getProjectId();

	String getContractId();

	Boolean getPublished();

	BigDecimal getMinAmount();

	BigDecimal getMaxAmount();

	String getProvince();

	String getCity();

}
