package cn.com.starest.nextoa.project.domain.model;

import java.math.BigDecimal;

/**
 * 待付款
 *
 * @author dz
 */
public class PendingPaymentAggregation {

	private BigDecimal totalAmount;

	private BigDecimal totalPayedAmount;

	private BigDecimal totalPendingAmount;

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalPayedAmount() {
		return totalPayedAmount;
	}

	public void setTotalPayedAmount(BigDecimal totalPayedAmount) {
		this.totalPayedAmount = totalPayedAmount;
	}

	public BigDecimal getTotalPendingAmount() {
		return totalPendingAmount;
	}

	public void setTotalPendingAmount(BigDecimal totalPendingAmount) {
		this.totalPendingAmount = totalPendingAmount;
	}
}
