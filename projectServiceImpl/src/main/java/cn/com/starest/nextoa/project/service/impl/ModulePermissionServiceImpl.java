package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.ModulePermission;
import cn.com.starest.nextoa.project.repository.ModulePermissionRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class ModulePermissionServiceImpl implements ModulePermissionService {

	@Autowired
	private ModulePermissionRepository modulePermissionRepository;

	@Override
	public Module[] listModuleFeatures() {
		return Module.values();
	}

	@Override
	public ModuleActions.ModuleAction[] listModuleActions(Module feature) {
		return ModuleActions.listAvailableActions(feature);
	}

	@Override
	public void grantPermission(Module feature, String action, User byWho) {
		ModulePermission modulePermission = modulePermissionRepository.findFirstByModuleAndModuleActionAndGrantedUser(
				feature,
				action,
				byWho);
		if (modulePermission == null) {
			modulePermission = new ModulePermission();
			modulePermission.setModule(feature);
			modulePermission.setModuleAction(action);
			modulePermission.setGrantedUser(byWho);
			modulePermissionRepository.save(modulePermission);
		}
	}

	@Override
	public void revokePermission(Module feature, String action, User byWho) {
		modulePermissionRepository.deleteByModuleAndModuleActionAndGrantedUser(feature, action, byWho);
	}

	@Override
	public boolean hasPermission(Module feature, String action, User byWho) {
		ModulePermission modulePermission = modulePermissionRepository.findFirstByModuleAndModuleActionAndGrantedUser(
				feature,
				action,
				byWho);

		return (modulePermission != null);
	}

	@Override
	public List<ModuleActions.ModuleAction> listGrantedActions(Module feature, User byWho) {
		return modulePermissionRepository.findListByModuleAndGrantedUser(feature, byWho).stream().map(item -> {
			String actionCode = item.getModuleAction();
			return ModuleActions.parseModuleAction(actionCode);
		}).filter(item -> item != null).collect(Collectors.toList());
	}

	@Override
	public List<User> listGrantedUsers(Module feature, String action) {
		return modulePermissionRepository.findListByModuleAndModuleAction(feature, action)
										 .stream()
										 .map(item -> item.getGrantedUser())
										 .filter(item -> item != null)
										 .collect(Collectors.toList());
	}

}
