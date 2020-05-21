package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;

/**
 * @author dz
 */
public class AbstractPermissionAware extends AbstractBaseSummary {

	private ModuleActions.ModuleAction[] grantedActions;

	public ModuleActions.ModuleAction[] getGrantedActions() {
		return grantedActions;
	}

	public void setGrantedActions(ModuleActions.ModuleAction[] grantedActions) {
		this.grantedActions = grantedActions;
	}

}
