package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.PendingPayment;

/**
 * @author dz
 */
public interface PendingPaymentReference {

	boolean hasReference(PendingPayment target);

}