package in.clouthink.nextoa.rbac.core.model;


/**
 *
 */
public interface Permission {

	/**
	 * The resource instance
	 * @return
	 */
	Resource getResource();

	/**
	 * The role which will participate in the resource actions
	 * @return
	 */
	String getRole();

	/**
	 * If the action is null or empty , it means any actions is allowed
	 * @return
	 */
	Action[] getAllowedActions();

	/**
	 * If the result is null or empty , it means no action is forbidden
	 * @return
	 */
	Action[] getForbiddenActions();

}
