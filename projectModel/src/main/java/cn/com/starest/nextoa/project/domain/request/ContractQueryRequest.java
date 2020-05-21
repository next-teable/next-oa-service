package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;

/**
 * @author dz
 */
public interface ContractQueryRequest extends PageQueryRequest {

	String getName();

	String getCode();

	String getProjectId();

	String getFirstPartyId();

	Boolean getPublished();

}
