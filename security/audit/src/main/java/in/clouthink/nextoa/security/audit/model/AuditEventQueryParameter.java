package in.clouthink.nextoa.security.audit.model;

import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class AuditEventQueryParameter extends DateRangedQueryParameter {

	private String requestedBy;

	private String requestedUrl;

	private Boolean error;

	private String clientAddress;

	private String serviceName;

	private String methodName;

	//大于或者等于这个时间,用于定位一些性能瓶颈
	private Long timeCost;

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getRequestedUrl() {
		return requestedUrl;
	}

	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Long getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(Long timeCost) {
		this.timeCost = timeCost;
	}
}
