package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;
import cn.com.starest.nextoa.project.domain.request.DepositQueryRequest;

/**
 * @author dz
 */
public class DepositQueryParameter extends DateRangedQueryParameter implements DepositQueryRequest {

	String depositTypeId;

	String companyId;

	String projectName;

	String projectId;

	String transferTo;

	String transferOperator;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getDepositTypeId() {
		return depositTypeId;
	}

	public void setDepositTypeId(String depositTypeId) {
		this.depositTypeId = depositTypeId;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}

	@Override
	public String getTransferOperator() {
		return transferOperator;
	}

	public void setTransferOperator(String transferOperator) {
		this.transferOperator = transferOperator;
	}
}
