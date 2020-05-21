package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Payment;

/**
 * @author dz
 */
public interface PaymentReference {

	boolean hasReference(Payment target);

}
