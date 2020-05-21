package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.ReimburseRequestRefer;
import cn.com.starest.nextoa.project.domain.request.SaveReimburseRequest;

import javax.validation.constraints.NotNull;

/**
 * @author dz
 */
public class SaveReimburseParameter extends AbstractSavePaymentParameter implements SaveReimburseRequest {

	@NotNull(message = "报销申请类型不能为空")
	private ReimburseRequestRefer requestReferType;

	@NotNull(message = "报销申请上下文不能为空")
	private String requestReferId;

	@Override
	public ReimburseRequestRefer getRequestReferType() {
		return requestReferType;
	}

	public void setRequestReferType(ReimburseRequestRefer requestReferType) {
		this.requestReferType = requestReferType;
	}

	@Override
	public String getRequestReferId() {
		return requestReferId;
	}

	public void setRequestReferId(String requestReferId) {
		this.requestReferId = requestReferId;
	}

}
