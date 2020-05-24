package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.ContactGroup;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ContactGroupSummary {

	static void convert(ContactGroup contactGroup, ContactGroupSummary result) {
		result.setId(contactGroup.getId());
		result.setName(contactGroup.getName());
	}

	public static ContactGroupSummary from(ContactGroup contactGroup) {
		if (contactGroup == null) {
			return null;
		}
		ContactGroupSummary result = new ContactGroupSummary();
		convert(contactGroup, result);
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
