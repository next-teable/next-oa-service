package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;

/**
 * The observer for CompanyReceivedPayment
 *
 * @author dz
 */
public interface CompanyReceivedPaymentObserver {

	/**
	 * @param companyReceivedPayment
	 */
	void onChange(CompanyReceivedPayment companyReceivedPayment);

}
