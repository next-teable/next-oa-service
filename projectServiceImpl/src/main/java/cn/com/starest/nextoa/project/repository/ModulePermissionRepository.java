package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.model.ModulePermission;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * ModulePermission repo
 */
public interface ModulePermissionRepository extends AbstractRepository<ModulePermission> {

	List<ModulePermission> findListByModuleAndModuleAction(Module module, String moduleAction);

	List<ModulePermission> findListByModuleAndGrantedUser(Module module, User grantedUser);

	ModulePermission findFirstByModuleAndModuleActionAndGrantedUser(Module module,
																	String moduleAction,
																	User grantedUser);

	void deleteByModuleAndModuleActionAndGrantedUser(Module module, String action, User byWho);

}