package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dz
 */
@Document(collection = "ModulePermissions")
public class ModulePermission extends StringIdentifier {

	@Indexed
	private Module module;

	@Indexed
	private String moduleAction;

	@Indexed
	@DBRef(lazy = true)
	private User grantedUser;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getModuleAction() {
		return moduleAction;
	}

	public void setModuleAction(String moduleAction) {
		this.moduleAction = moduleAction;
	}

	public User getGrantedUser() {
		return grantedUser;
	}

	public void setGrantedUser(User grantedUser) {
		this.grantedUser = grantedUser;
	}
}
