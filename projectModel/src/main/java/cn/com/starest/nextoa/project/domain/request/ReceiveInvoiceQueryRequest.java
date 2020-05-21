package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface ReceiveInvoiceQueryRequest extends DateRangedQueryRequest {

	String getProjectId();

	String getSubContractId();

	String getSubOrderId();

	String getSubContractorId();

	BigDecimal getMinAmount();

	BigDecimal getMaxAmount();
}
