package cn.com.starest.nextoa.project.domain.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author dz
 */
public class DefaultReimburseContext implements ReimburseContext {

	@NotEmpty(message = "报销申请类型不能为空")
	ReimburseRequestRefer requestReferType;

	@NotEmpty(message = "报销申请上下文不能为空")
	String requestReferId;

	public DefaultReimburseContext() {
	}

	public DefaultReimburseContext(ReimburseRequestRefer requestReferType, String requestReferId) {
		this.requestReferId = requestReferId;
		this.requestReferType = requestReferType;
	}

	@Override
	public String getRequestReferId() {
		return requestReferId;
	}

	public void setRequestReferId(String requestReferId) {
		this.requestReferId = requestReferId;
	}

	@Override
	public ReimburseRequestRefer getRequestReferType() {
		return requestReferType;
	}

	public void setRequestReferType(ReimburseRequestRefer requestReferType) {
		this.requestReferType = requestReferType;
	}

}
