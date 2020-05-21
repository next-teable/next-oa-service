package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Module;

import java.util.List;

/**
 * 模块权限管理
 * <p>
 * <ul>
 * <li>粒度到增删改查</li>
 * <li>授权到用户（不支持角色）</li>
 * </ul>
 *
 * @author dz
 */
public interface ModulePermissionService {

	/**
	 * @return
	 */
	Module[] listModuleFeatures();

	/**
	 * @return
	 */
	ModuleActions.ModuleAction[] listModuleActions(Module feature);

	/**
	 * 授权
	 *
	 * @param feature
	 * @param action
	 * @param byWho
	 */
	void grantPermission(Module feature, String action, User byWho);

	/**
	 * 收回权限
	 *
	 * @param feature
	 * @param action
	 * @param byWho
	 */
	void revokePermission(Module feature, String action, User byWho);

	/**
	 * 判断是否有权限
	 *
	 * @param feature
	 * @param action
	 * @param byWho
	 * @return
	 */
	boolean hasPermission(Module feature, String action, User byWho);

	/**
	 * 列出已授权的操作
	 *
	 * @param feature
	 * @param byWho
	 * @return
	 */
	List<ModuleActions.ModuleAction> listGrantedActions(Module feature, User byWho);

	/**
	 * 列出已授权的用户
	 *
	 * @param feature
	 * @param action
	 * @return
	 */
	List<User> listGrantedUsers(Module feature, String action);




}
