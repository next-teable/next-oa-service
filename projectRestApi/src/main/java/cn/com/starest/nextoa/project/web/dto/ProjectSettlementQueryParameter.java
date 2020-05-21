package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.request.ProjectSettlementQueryRequest;

/**
 * @author dz
 */
public class ProjectSettlementQueryParameter extends DateRangedQueryParameter implements ProjectSettlementQueryRequest {

	String projectId;

	String contractId;

	String orderId;

	Boolean settled;

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

	public Boolean getSettled() {
		return settled;
	}

	public void setSettled(Boolean settled) {
		this.settled = settled;
	}
}
