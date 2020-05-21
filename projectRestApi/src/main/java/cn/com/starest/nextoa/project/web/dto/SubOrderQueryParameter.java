package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class SubOrderQueryParameter extends PageQueryParameter implements SubOrderQueryRequest {

	String name;

	String code;

	String projectId;

	String orderId;

	String subContractId;

	BigDecimal minAmount;

	BigDecimal maxAmount;

	Boolean published;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
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
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

}
