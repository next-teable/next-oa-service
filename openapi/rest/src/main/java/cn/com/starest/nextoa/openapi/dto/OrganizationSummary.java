package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Organization;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class OrganizationSummary {

	static void convert(Organization organization, OrganizationSummary result) {
		result.setId(organization.getId());
		result.setCode(organization.getCode());
		result.setName(organization.getName());
		result.setLeaf(organization.isLeaf());
		if (organization.getParent() != null) {
			result.setParentId(organization.getParent().getId());
			result.setParentName(organization.getParent().getName());
		}
	}

	public static OrganizationSummary from(Organization organization) {
		if (organization == null) {
			return null;
		}
		OrganizationSummary result = new OrganizationSummary();
		convert(organization, result);
		return result;
	}

	private String id;

	private String code;

	private String name;

	private String parentId;

	private String parentName;

	private boolean leaf;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
}
