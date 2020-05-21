package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

/**
 * @author dz
 */
public interface ProjectCompletionQueryRequest extends DateRangedQueryRequest {

	String getProjectId();

	String getContractId();

	String getOrderId();

}
