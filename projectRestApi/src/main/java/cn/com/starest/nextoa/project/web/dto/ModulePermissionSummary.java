package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Module;

import java.util.List;

/**
 * @author dz
 */
public class ModulePermissionSummary {

	public static class GrantedModuleAction {

		ModuleActions.ModuleAction moduleAction;

		boolean granted;

		public GrantedModuleAction() {
		}

		public GrantedModuleAction(ModuleActions.ModuleAction moduleAction, boolean granted) {
			this.moduleAction = moduleAction;
			this.granted = granted;
		}

		public ModuleActions.ModuleAction getModuleAction() {
			return moduleAction;
		}

		public void setModuleAction(ModuleActions.ModuleAction moduleAction) {
			this.moduleAction = moduleAction;
		}

		public boolean isGranted() {
			return granted;
		}

		public void setGranted(boolean granted) {
			this.granted = granted;
		}
	}

	private Module module;

	private List<GrantedModuleAction> actions;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<GrantedModuleAction> getActions() {
		return actions;
	}

	public void setActions(List<GrantedModuleAction> actions) {
		this.actions = actions;
	}

}
