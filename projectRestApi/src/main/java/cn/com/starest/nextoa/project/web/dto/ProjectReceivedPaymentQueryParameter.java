package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import cn.com.starest.nextoa.project.domain.request.ProjectReceivedPaymentQueryRequest;

/**
 * @author dz
 */
public class ProjectReceivedPaymentQueryParameter extends DateRangedQueryParameter implements
																				   ProjectReceivedPaymentQueryRequest {

	private String projectId;

	private String contractId;

	private String orderId;

	private ReceivedPaymentType type;

	private String accountSubjectId;

	private String code;

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
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
	}

	@Override
	public String getAccountSubjectId() {
		return accountSubjectId;
	}

	public void setAccountSubjectId(String accountSubjectId) {
		this.accountSubjectId = accountSubjectId;
	}
}
