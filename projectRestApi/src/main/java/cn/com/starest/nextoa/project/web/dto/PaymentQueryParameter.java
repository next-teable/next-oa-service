package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.PaymentSource;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;

/**
 * @author dz
 */
public class PaymentQueryParameter extends PageQueryParameter implements PaymentQueryRequest {

	private String companyId;

	private String projectId;

	private String bizDepartmentId;

	private String subContractorId;

	private String payToId;

	private String accountSubjectId;

	private String subAccountSubjectId;

	private PaymentSource source;

	private Integer year;

	private String orderId;

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getBizDepartmentId() {
		return bizDepartmentId;
	}

	public void setBizDepartmentId(String bizDepartmentId) {
		this.bizDepartmentId = bizDepartmentId;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}

	@Override
	public String getPayToId() {
		return payToId;
	}

	public void setPayToId(String payToId) {
		this.payToId = payToId;
	}

	@Override
	public String getAccountSubjectId() {
		return accountSubjectId;
	}

	public void setAccountSubjectId(String accountSubjectId) {
		this.accountSubjectId = accountSubjectId;
	}

	@Override
	public String getSubAccountSubjectId() {
		return subAccountSubjectId;
	}

	public void setSubAccountSubjectId(String subAccountSubjectId) {
		this.subAccountSubjectId = subAccountSubjectId;
	}

	@Override
	public PaymentSource getSource() {
		return source;
	}

	public void setSource(PaymentSource source) {
		this.source = source;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
