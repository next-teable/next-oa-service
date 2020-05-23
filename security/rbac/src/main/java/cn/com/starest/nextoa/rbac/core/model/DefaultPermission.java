package cn.com.starest.nextoa.rbac.core.model;

/**
 */
public class DefaultPermission implements Permission {

	private Resource resource;

	private String role;

	private Action[] allowedActions;

	private Action[] forbiddenActions;

	@Override
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Action[] getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(Action[] allowedActions) {
		this.allowedActions = allowedActions;
	}

	@Override
	public Action[] getForbiddenActions() {
		return forbiddenActions;
	}

	public void setForbiddenActions(Action[] forbiddenActions) {
		this.forbiddenActions = forbiddenActions;
	}
}
