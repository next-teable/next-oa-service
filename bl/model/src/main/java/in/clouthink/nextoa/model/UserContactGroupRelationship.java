package in.clouthink.nextoa.model;

import in.clouthink.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "UserContractGroupRelationships")
public class UserContactGroupRelationship extends StringIdentifier {

	@Indexed
	@DBRef
	private ContactGroup contactGroup;

	@Indexed
	@DBRef
	private User user;

	@DBRef
	private User createdBy;

	private Date createdAt;

	public ContactGroup getContactGroup() {
		return contactGroup;
	}

	public void setContactGroup(ContactGroup contactGroup) {
		this.contactGroup = contactGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
