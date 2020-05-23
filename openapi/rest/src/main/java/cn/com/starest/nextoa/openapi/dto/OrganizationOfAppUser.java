package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Organization;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApiModel
public class OrganizationOfAppUser {

	public static void convert(Organization organization, OrganizationOfAppUser result) {
		result.setId(organization.getId());
		result.setName(organization.getName());
		result.setParentIds(new String[0]);
		if (organization.getParent() != null) {
			result.setParentId(organization.getParent().getId());
			result.setParentName(organization.getParent().getName());
			List<String> parentIds = new ArrayList<>();
			iterateOrganization(organization.getParent(), parentIds);
			Collections.reverse(parentIds);
			result.setParentIds(parentIds.toArray(new String[0]));
		}
	}

	private static void iterateOrganization(Organization organization, List<String> ids) {
		ids.add(organization.getId());
		if (organization.getParent() != null) {
			iterateOrganization(organization.getParent(), ids);
		}
	}

	public static OrganizationOfAppUser from(Organization organization) {
		if (organization == null) {
			return null;
		}
		OrganizationOfAppUser result = new OrganizationOfAppUser();
		convert(organization, result);
		return result;
	}

	private String id;
	private String name;
	private String parentId;
	private String parentName;
	private String[] parentIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String[] getParentIds() {
		return parentIds;
	}

	public void setParentIds(String[] parentIds) {
		this.parentIds = parentIds;
	}
}
