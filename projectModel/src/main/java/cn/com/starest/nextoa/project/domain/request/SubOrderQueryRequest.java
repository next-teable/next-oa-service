package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SubOrderQueryRequest extends PageQueryRequest {

	String getName();

	String getCode();

	String getProjectId();

	String getOrderId();

	String getSubContractId();

	BigDecimal getMinAmount();

	BigDecimal getMaxAmount();

	Boolean getPublished();
}
