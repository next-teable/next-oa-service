package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Payment;

/**
 * @author dz
 */
public class PaymentDetail extends PaymentSummary {

	public static void convert(Payment fromObj, PaymentDetail result) {
		PaymentSummary.convert(fromObj, result);
	}

	public static PaymentDetail from(Payment fromObj) {
		if (fromObj == null) {
			return null;
		}
		PaymentDetail result = new PaymentDetail();
		convert(fromObj, result);
		return result;
	}

	private PendingPaymentSummary pendingPayment;

	public PendingPaymentSummary getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(PendingPaymentSummary pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

}
