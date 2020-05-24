package in.clouthink.nextoa.security.rbac.impl.model;

import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 */
@Document(collection = "ResourceRoleRelationships")
public class ResourceRoleRelationship extends StringIdentifier {

	@Indexed
	private String resourceCode;

	@Indexed
	private String roleCode;

	private List<String> allowedActions;

	private List<String> forbiddenActions;

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<String> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<String> allowedActions) {
		this.allowedActions = allowedActions;
	}

	public List<String> getForbiddenActions() {
		return forbiddenActions;
	}

	public void setForbiddenActions(List<String> forbiddenActions) {
		this.forbiddenActions = forbiddenActions;
	}

}
