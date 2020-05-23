package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.ContactGroup;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class ContactGroupDetail extends ContactGroupSummary {

	public static ContactGroupDetail from(ContactGroup contactGroup, List<UserSummary> userSummaries) {
		if (contactGroup == null) {
			return null;
		}
		ContactGroupDetail result = new ContactGroupDetail();
		convert(contactGroup, result);
		result.setUsers(userSummaries);
		return result;
	}

	private List<UserSummary> users;

	public List<UserSummary> getUsers() {
		return users;
	}

	public void setUsers(List<UserSummary> users) {
		this.users = users;
	}

}
