package in.clouthink.nextoa.security.rbac.core.model;


import java.util.List;

/**
 * The resource with children presents the tree structure for resource
 */
public interface ResourceWithChildren extends Resource {

	/**
	 * @return false if no children available
	 */
	boolean hasChildren();

	List<? extends ResourceWithChildren> getChildren();

}
