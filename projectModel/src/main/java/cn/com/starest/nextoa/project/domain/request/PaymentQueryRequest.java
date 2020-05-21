package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.PaymentSource;

/**
 * @author dz
 */
public interface PaymentQueryRequest extends PageQueryRequest {

	String getCompanyId();

	String getProjectId();

	String getBizDepartmentId();

	String getSubContractorId();

	String getPayToId();

	String getAccountSubjectId();

	String getSubAccountSubjectId();

	String getOrderId();

	PaymentSource getSource();

	Integer getYear();

}
