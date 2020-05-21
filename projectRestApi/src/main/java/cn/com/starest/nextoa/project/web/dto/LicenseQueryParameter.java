package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.request.LicenseQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class LicenseQueryParameter extends DateRangedQueryParameter implements LicenseQueryRequest {

	String code;

	String projectId;

	String contractId;

	String orderId;

	BigDecimal minAmount;

	BigDecimal maxAmount;

	Boolean cancelled;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	@Override
	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}
}
