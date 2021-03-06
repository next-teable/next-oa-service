package in.clouthink.nextoa.security.rbac.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultResourceWithChildren extends DefaultResource implements ResourceWithChildren {

	private List<ResourceWithChildren> children = new ArrayList<>();

	@Override
	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	@JsonDeserialize(contentAs = DefaultResourceWithChildren.class)
	public List<ResourceWithChildren> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceWithChildren> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return super.toString() + "/DefaultResourceWithChildren{" +
			   "children=" + children +
			   '}';
	}
}
