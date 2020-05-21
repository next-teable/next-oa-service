package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;

/**
 * @author dz
 */
public class ContractQueryParameter extends PageQueryParameter implements ContractQueryRequest {

	String name;

	String code;

	String projectId;

	String firstPartyId;

	Boolean published;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	@Override
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
}
