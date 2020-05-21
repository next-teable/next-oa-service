package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;

/**
 * The observer for ProjectReceivedPayment
 *
 * @author dz
 */
public interface ProjectReceivedPaymentObserver {

	/**
	 * @param projectReceivedPayment
	 */
	void onChange(ProjectReceivedPayment projectReceivedPayment);

}
