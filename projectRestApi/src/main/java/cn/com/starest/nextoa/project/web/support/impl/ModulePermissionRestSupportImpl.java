package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.exception.UserNotFoundException;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.web.dto.GrantModuleParameter;
import cn.com.starest.nextoa.project.web.dto.ModulePermissionSummary;
import cn.com.starest.nextoa.project.web.support.ModulePermissionRestSupport;
import cn.com.starest.nextoa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
@Component
public class ModulePermissionRestSupportImpl implements ModulePermissionRestSupport {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public List<ModulePermissionSummary> listModulePermissions(String userId) {
		User user = accountService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("无效的用户ID");
		}
		List<ModulePermissionSummary> result = new ArrayList<>();
		Module[] features = modulePermissionService.listModuleFeatures();
		for (Module feature : features) {
			ModuleActions.ModuleAction[] allActions = modulePermissionService.listModuleActions(feature);
			List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(feature, user);

			List<ModulePermissionSummary.GrantedModuleAction> actions = new ArrayList<ModulePermissionSummary.GrantedModuleAction>();
			for (ModuleActions.ModuleAction action : allActions) {
				actions.add(new ModulePermissionSummary.GrantedModuleAction(action, grantedActions.contains(action)));
			}

			ModulePermissionSummary item = new ModulePermissionSummary();
			item.setModule(feature);
			item.setActions(actions);
			result.add(item);
		}
		return result;
	}

	@Override
	public void saveModulePermissions(String userId, GrantModuleParameter request) {
		User user = accountService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException("无效的用户ID");
		}
		ModuleActions.ModuleAction[] allActions = modulePermissionService.listModuleActions(request.getModule());
		for (ModuleActions.ModuleAction action : allActions) {
			if (request.getActions().contains(action.getCode())) {
				modulePermissionService.grantPermission(request.getModule(), action.getCode(), user);
			}
			else {
				modulePermissionService.revokePermission(request.getModule(), action.getCode(), user);
			}
		}

	}

}
