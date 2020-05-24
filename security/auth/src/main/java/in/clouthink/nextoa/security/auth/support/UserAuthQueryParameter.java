package in.clouthink.nextoa.security.auth.support;

import in.clouthink.nextoa.security.auth.model.AuthEventQueryRequest;
import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;

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
