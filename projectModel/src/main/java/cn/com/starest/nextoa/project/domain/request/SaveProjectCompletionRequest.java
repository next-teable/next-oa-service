package cn.com.starest.nextoa.project.domain.request;

import java.util.Date;

/**
 * @author dz
 */
public interface SaveProjectCompletionRequest {

	String getProjectId();

	String getContractId();

	String getOrderId();

	Date getCompleteAt();

	String getDescription();

}
