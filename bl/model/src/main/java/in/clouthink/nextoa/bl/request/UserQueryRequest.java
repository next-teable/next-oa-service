package in.clouthink.nextoa.bl.request;


import in.clouthink.nextoa.bl.model.UserType;

public interface UserQueryRequest extends UsernameQueryRequest {

	/**
	 *
	 * @return 联系电话
	 */
	String getContactPhone();

	/**
	 *
	 * @return 办公电话
	 */
	String getOfficePhone();

	/**
	 *
	 * @return 电子邮箱
	 */
	String getEmail();

	/**
	 *
	 * @return 所属组织机构id
	 */
	String getOrganizationId();

	/**
	 *
	 * @return 是否启用(或禁用)
	 */
	Boolean getEnabled();

	/**
	 *
	 * @return 用户类型(系统用户,应用用户)
	 */
	UserType getUserType();

	/**
	 *
	 * @return 职位
	 */
	String getPosition();

}
