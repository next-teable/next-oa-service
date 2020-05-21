package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;

/**
 * @author dz
 */
public interface ProjectReceivedPaymentReference {

	boolean hasReference(ProjectReceivedPayment target);

}
