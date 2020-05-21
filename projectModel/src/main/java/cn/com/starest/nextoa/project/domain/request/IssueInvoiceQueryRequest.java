package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface IssueInvoiceQueryRequest extends DateRangedQueryRequest {

	String getProjectId();

	String getContractId();

	String getOrderId();

	String getFirstPartyId();

	BigDecimal getMinAmount();

	BigDecimal getMaxAmount();
}
