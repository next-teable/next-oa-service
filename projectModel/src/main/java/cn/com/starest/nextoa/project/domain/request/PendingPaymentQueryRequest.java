package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface PendingPaymentQueryRequest extends PageQueryRequest {

	String getCompanyId();

	String getProjectId();

	String getBizDepartmentId();

	String getReceivedPaymentCode();

	String getSubContractorId();

}
