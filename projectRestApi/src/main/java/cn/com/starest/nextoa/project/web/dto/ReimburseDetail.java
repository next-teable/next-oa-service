package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Reimburse;

/**
 * @author dz
 */
public class ReimburseDetail extends ReimburseSummary {

	public static void convert(Reimburse fromObj, ReimburseDetail result) {
		ReimburseSummary.convert(fromObj, result);
	}

	public static ReimburseDetail from(Reimburse fromObj) {
		if (fromObj == null) {
			return null;
		}
		ReimburseDetail result = new ReimburseDetail();
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
