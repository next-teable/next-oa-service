package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;

/**
 * @author dz
 */
public interface CompanyReceivedPaymentQueryRequest extends DateRangedQueryRequest {

	String getCode();

	String getCompanyId();

	ReceivedPaymentType getType();

	String getAccountSubjectId();

	String getSubAccountSubjectId();

	String getReceivedFrom();

}
