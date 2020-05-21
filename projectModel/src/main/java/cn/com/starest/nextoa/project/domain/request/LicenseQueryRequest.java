package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface LicenseQueryRequest extends DateRangedQueryRequest {

	String getCode();

	String getProjectId();

	String getContractId();

	String getOrderId();

	Boolean getCancelled();

	BigDecimal getMinAmount();

	BigDecimal getMaxAmount();

}
