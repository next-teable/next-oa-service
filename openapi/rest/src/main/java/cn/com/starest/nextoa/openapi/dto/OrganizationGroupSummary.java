package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Group;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class OrganizationGroupSummary {

	static void convert(Group group, OrganizationGroupSummary result) {
		result.setId(group.getId());
		result.setName(group.getName());
		//TODO add null check
		result.setOrganizationId(group.getOrganization().getId());
	}

	public static OrganizationGroupSummary from(Group group) {
		if (group == null) {
			return null;
		}
		OrganizationGroupSummary result = new OrganizationGroupSummary();
		convert(group, result);
		return result;
	}

	private String id;

	private String name;

	private String organizationId;

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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
}
