package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Organization;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SimpleOrganization {

	static void convert(Organization organization, SimpleOrganization result) {
		result.setId(organization.getId());
		result.setName(organization.getName());
		if (organization.getParent() != null) {
			result.setName(organization.getParent().getName() +
						   "_" +
						   organization.getName());
		}
		else {
			result.setName(organization.getName());
		}
	}

	public static SimpleOrganization from(Organization organization) {
		if (organization == null) {
			return null;
		}
		SimpleOrganization result = new SimpleOrganization();
		convert(organization, result);
		return result;
	}

	private String id;

	private String name;

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

}
