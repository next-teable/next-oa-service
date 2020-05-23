package cn.com.starest.nextoa.dashboard.support.auth.support;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEventQueryRequest;
import cn.com.starest.nextoa.openapi.dto.DateRangedQueryParameter;

/**
 */
public class UserAuthQueryParameter extends DateRangedQueryParameter implements AuthEventQueryRequest {

	private String username;

	private Boolean succeed;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}
}
