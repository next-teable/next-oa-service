package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface SubContractQueryRequest extends PageQueryRequest {

	String getName();

	String getCode();

	String getProjectId();

	String getContractId();

	String getSubContractorId();

	Boolean getPublished();

}
