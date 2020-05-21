package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import cn.com.starest.nextoa.project.domain.request.CompanyReceivedPaymentQueryRequest;

/**
 * @author dz
 */
public class CompanyReceivedPaymentQueryParameter extends DateRangedQueryParameter implements
																				   CompanyReceivedPaymentQueryRequest {

	String code;

	String companyId;

	ReceivedPaymentType type;

	String accountSubjectId;

	String subAccountSubjectId;

	String receivedFrom;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	@Override
	public String getSubAccountSubjectId() {
		return subAccountSubjectId;
	}

	public void setSubAccountSubjectId(String subAccountSubjectId) {
		this.subAccountSubjectId = subAccountSubjectId;
	}

	@Override
	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}
}
