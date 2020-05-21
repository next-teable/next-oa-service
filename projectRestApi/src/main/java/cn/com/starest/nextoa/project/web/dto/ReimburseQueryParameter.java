package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.ReimburseQueryRequest;
import cn.com.starest.nextoa.project.domain.request.ReimburseRequestRefer;

/**
 * @author dz
 */
public class ReimburseQueryParameter extends PageQueryParameter implements ReimburseQueryRequest {

	private ReimburseRequestRefer requestReferType;

	private String requestReferId;

	private String projectId;

	private String bizDepartmentId;

	private Boolean settled;

	@Override
	public String getRequestReferId() {
		return requestReferId;
	}

	public void setRequestReferId(String requestReferId) {
		this.requestReferId = requestReferId;
	}

	@Override
	public ReimburseRequestRefer getRequestReferType() {
		return requestReferType;
	}

	public void setRequestReferType(ReimburseRequestRefer requestReferType) {
		this.requestReferType = requestReferType;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getBizDepartmentId() {
		return bizDepartmentId;
	}

	public void setBizDepartmentId(String bizDepartmentId) {
		this.bizDepartmentId = bizDepartmentId;
	}

	@Override
	public Boolean getSettled() {
		return settled;
	}

	public void setSettled(Boolean settled) {
		this.settled = settled;
	}

}
