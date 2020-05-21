package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.request.PendingPaymentQueryRequest;

/**
 * @author dz
 */
public class PendingPaymentQueryParameter extends DateRangedQueryParameter implements PendingPaymentQueryRequest {

	String companyId;

	String projectId;

	String bizDepartmentId;

	String receivedPaymentCode;

	String subContractorId;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getBizDepartmentId() {
		return bizDepartmentId;
	}

	public void setBizDepartmentId(String bizDepartmentId) {
		this.bizDepartmentId = bizDepartmentId;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getReceivedPaymentCode() {
		return receivedPaymentCode;
	}

	public void setReceivedPaymentCode(String receivedPaymentCode) {
		this.receivedPaymentCode = receivedPaymentCode;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}
}
