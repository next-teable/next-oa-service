package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.domain.model.ProjectType;

/**
 * @author dz
 */
public interface ProjectQueryRequest extends PageQueryRequest {

	enum ProjectStatusScope {
		INCLUDE, EXCLUDE
	}

	String getCompanyId();

	String getName();

	String getCode();

	String getFrameworkContractId();

	ProjectType getProjectType();

	ProjectType[] getProjectTypes();

	ProjectDeliveryType getDeliveryType();

	ProjectStatus getStatus();

	ProjectStatusScope getStatusScope();

	String getFirstPartyId();

	String getManagerId();

	String getSortOption();

//	User getCreatedBy();

}
