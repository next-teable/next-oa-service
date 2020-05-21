package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.domain.model.ProjectType;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;

/**
 * @author dz
 */
public class ProjectQueryParameter extends PageQueryParameter implements ProjectQueryRequest {

	private String companyId;

	private String name;

	private String code;

	private String frameworkContractId;

	private ProjectType projectType;

	private ProjectType[] projectTypes;

	private ProjectDeliveryType deliveryType;

	private ProjectStatus status;

	private ProjectStatusScope statusScope;

	private String firstPartyId;

	private String managerId;

	private String sortOption;

	private String subAccountSubjectId;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

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
	public String getFrameworkContractId() {
		return frameworkContractId;
	}

	public void setFrameworkContractId(String frameworkContractId) {
		this.frameworkContractId = frameworkContractId;
	}

	@Override
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	@Override
	public ProjectType[] getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(ProjectType[] projectTypes) {
		this.projectTypes = projectTypes;
	}

	@Override
	public ProjectDeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(ProjectDeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Override
	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	@Override
	public ProjectStatusScope getStatusScope() {
		return statusScope;
	}

	public void setStatusScope(ProjectStatusScope statusScope) {
		this.statusScope = statusScope;
	}

	@Override
	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	@Override
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String getSortOption() {
		return sortOption;
	}

	public void setSortOption(String sortOption) {
		this.sortOption = sortOption;
	}

	public String getSubAccountSubjectId() {
		return subAccountSubjectId;
	}

	public void setSubAccountSubjectId(String subAccountSubjectId) {
		this.subAccountSubjectId = subAccountSubjectId;
	}
}
