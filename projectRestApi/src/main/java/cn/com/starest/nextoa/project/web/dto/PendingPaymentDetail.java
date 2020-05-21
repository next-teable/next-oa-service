package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.PendingPayment;

/**
 * @author dz
 */
public class PendingPaymentDetail extends PendingPaymentSummary {

	public static void convert(PendingPayment fromObj, PendingPaymentDetail result) {
		PendingPaymentSummary.convert(fromObj, result);
	}

	public static PendingPaymentDetail from(PendingPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		PendingPaymentDetail result = new PendingPaymentDetail();
		convert(fromObj, result);
		return result;
	}

	private PaymentSummary payment;

	//备注
	private String description;

	public PaymentSummary getPayment() {
		return payment;
	}

	public void setPayment(PaymentSummary payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
