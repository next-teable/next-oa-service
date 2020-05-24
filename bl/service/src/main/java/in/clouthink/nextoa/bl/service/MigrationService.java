package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.User;

/**
 * 数据迁移服务
 */
public interface MigrationService {

	/**
	 * 迁移用户的组织机构（user#organization ->  user organization relationship）
	 *
	 * @param byWho
	 */
	void migrateUserOrganizationRelationships(User byWho);

	/**
	 * 设置所有没有子节点的部门的leaf属性为true
	 *
	 * @param user
	 */
	void updateOrganizationLeafStatus(User user);

	/**
	 * AppUser的部门为空且没有归档的,设置到临时部门
	 *
	 * @param user
	 */
	void fixAppUserNullOrganization(User user);

	/**
	 * 更新paper的版本号,所有版本号为空或者为0的表示是遗留数据,需要更新为1
	 *
	 * @param user
	 */
	void updatePaperVersion(User user);

	/**
	 * 为提高性能, 在PaperTransition中增加creatorId,减少关联查询
	 *
	 * @param user
	 */
	void updatePaperTransitionCreator(User user);

	/**
	 * 为提高查询性能,需要将快文的category复制到message上作为冗余字段
	 *
	 * @param user
	 */
	void updateMessageCategory(User user);

	/**
	 * 修复通知的阅读总量
	 *
	 * @param user
	 */
	void fixNoticeReadCounter(User user);

}
