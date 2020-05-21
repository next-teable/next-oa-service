package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentSource;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SavePendingPaymentRequest {

	int getYear();

	/**
	 * @return 回款源（公司还是项目回款）
	 */
	ReceivedPaymentSource getReceivedPaymentSource();

	/**
	 * @return 回款sn
	 */
	String getReceivedPaymentCode();

	/**
	 * @return 待金额
	 */
	BigDecimal getAmount();

	/**
	 * @return 待付款单位（分包单位）
	 */
	String getSubContractorId();

	/**
	 * @return 备注
	 */
	String getDescription();

}
