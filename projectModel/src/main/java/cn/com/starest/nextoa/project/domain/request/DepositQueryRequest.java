package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

/**
 * @author dz
 */
public interface DepositQueryRequest extends DateRangedQueryRequest {

	String getDepositTypeId();

	String getProjectName();

	String getCompanyId();

	String getProjectId();

	String getTransferTo();

	String getTransferOperator();

}
