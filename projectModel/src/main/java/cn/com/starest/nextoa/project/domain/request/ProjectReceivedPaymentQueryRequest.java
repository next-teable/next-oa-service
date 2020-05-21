package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;

/**
 * @author dz
 */
public interface ProjectReceivedPaymentQueryRequest extends DateRangedQueryRequest {

	String getCode();

	String getProjectId();

	String getContractId();

	String getOrderId();

	ReceivedPaymentType getType();

	String getAccountSubjectId();

}
