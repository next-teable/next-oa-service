package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveReimbursePendingPaymentRequest {

	/**
	 * 报销选择外协单位支付时，必须选择对应的待支付数据（根据付款单位带出待付款），
	 * 选择对应待付款后，系统自动让选择的待付款数据失效，同时在对应待支付数据后填入付款时间。不对比待付款金额与实际报销金额。
	 *
	 * @return
	 */
	String getPendingPaymentId();


	BigDecimal getAmount();

}
