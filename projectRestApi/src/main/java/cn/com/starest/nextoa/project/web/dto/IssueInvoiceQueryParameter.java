package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.request.IssueInvoiceQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class IssueInvoiceQueryParameter extends DateRangedQueryParameter implements IssueInvoiceQueryRequest {

	String projectId;

	String contractId;

	String orderId;

	String firstPartyId;

	BigDecimal minAmount;

	BigDecimal maxAmount;

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	@Override
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	@Override
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}
}
