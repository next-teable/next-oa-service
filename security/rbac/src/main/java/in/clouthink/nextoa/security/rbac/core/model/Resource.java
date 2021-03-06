package in.clouthink.nextoa.security.rbac.core.model;


import java.util.List;
import java.util.Map;

/**
 * The resource abstraction
 */
public interface Resource {

	/**
	 * The virtual resource means no more extra operation available and just as the parent container of children.
	 * And when the resource is virtual , the expression is ignored whatever it's empty or not.
	 *
	 * @return false by default
	 */
	boolean isVirtual();

	/**
	 * Open for user, not special access control required
	 *
	 * @return false by default
	 */
	boolean isOpen();

	/**
	 * The resource code must be unique in global (include the children)
	 *
	 * @return
	 */
	String getCode();

	/**
	 * The resource name must be unique in same level
	 *
	 * @return
	 */
	String getName();

	/**
	 * The type expression can present what the resource belongs to .
	 * For example:
	 * menu:portal -> the menu for portal
	 * api:user -> the api for user
	 *
	 * @return
	 */
	String getType();

	/**
	 * @return
	 */
	Resource getParent();

	/**
	 * Can't be empty if the resource is not virtual resource
	 *
	 * @return
	 */
	String getExpression();

	List<String> getPatterns();

	/**
	 * The  available actions for the resource.
	 * for example :
	 * GET the resource
	 * DELETE the resource
	 * UPDATE the resource
	 * CREATE the resource
	 *
	 * @return
	 */

	List<Action> getActions();

	/**
	 * The extra metadata for the resource.
	 * for example : the icon to present the resource
	 *
	 * @return
	 */
	Map getMetadata();

}
