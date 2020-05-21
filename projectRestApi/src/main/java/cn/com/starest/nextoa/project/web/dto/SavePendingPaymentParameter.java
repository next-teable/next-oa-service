package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentSource;
import cn.com.starest.nextoa.project.domain.request.SavePendingPaymentRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SavePendingPaymentParameter implements SavePendingPaymentRequest {

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	@NotNull(message = "回款类型不能为空")
	private ReceivedPaymentSource receivedPaymentSource;

	@NotNull(message = "回款流程ID不能为空")
	String receivedPaymentCode;

	//付款金额*
	@NotNull(message = "待付款金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "待付款金额不能小于零")
	BigDecimal amount;

	//付款单位（分包单位）
	@NotNull(message = "待付款单位不能为空")
	String subContractorId;

	//备注
	String description;

	@Override
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public ReceivedPaymentSource getReceivedPaymentSource() {
		return receivedPaymentSource;
	}

	public void setReceivedPaymentSource(ReceivedPaymentSource receivedPaymentSource) {
		this.receivedPaymentSource = receivedPaymentSource;
	}

	@Override
	public String getReceivedPaymentCode() {
		return receivedPaymentCode;
	}

	public void setReceivedPaymentCode(String receivedPaymentCode) {
		this.receivedPaymentCode = receivedPaymentCode;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.amount = amount;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
