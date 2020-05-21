package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;

/**
 * @author dz
 */
public interface CompanyReceivedPaymentReference {

	boolean hasReference(CompanyReceivedPayment target);

}
