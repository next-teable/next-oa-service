package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.Gender;
import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserType;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@ApiModel
public class UserSummary {

	static void convert(User user, UserSummary result) {
		BeanUtils.copyProperties(user, result, "expired", "locked", "organization", "organizations");
		List<Organization> organizations = user.getOrganizations();
		if (organizations != null && !organizations.isEmpty()) {
			result.setOrganizations(organizations.stream().map(SimpleOrganization::from).collect(Collectors.toList()));
		}
	}

	public static UserSummary from(User user) {
		if (user == null) {
			return null;
		}
		UserSummary result = new UserSummary();
		convert(user, result);
		return result;
	}

	private String id;

	private String username;

	private UserType userType;

	private String avatarId;

	private String contactPhone;

	private String officePhone;

	private String email;

	private String extensionNumber;

	private String position;

	private String rank;

	private Gender gender;

	private Date birthday;

	private boolean restricted;

	private boolean enabled;

	@Deprecated
	private String organizationId;

	@Deprecated
	private String organizationName;

	private List<SimpleOrganization> organizations;

	private Date createdAt;

	private Date deletedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isRestricted() {
		return restricted;
	}

	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public List<SimpleOrganization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<SimpleOrganization> organizations) {
		this.organizations = organizations;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}
