package in.clouthink.nextoa.bl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document(collection = "Users")
@CompoundIndexes({@CompoundIndex(name = "user_username_usertype", def = "{username : 1, userType : 1}")})
public class User extends StringIdentifier implements UserDetails {

	public static final String ADMINISTRATOR = "administrator";

	// 用户帐号名
	@Indexed
	private String username;

	@Indexed
	private String pinyin;

	// 联系电话
	@Indexed
	private String contactPhone;

	// 办公电话
	@Indexed
	private String officePhone;

	// 短号码 或 内线号码
	private String extensionNumber;

	// 电子邮箱
	@Indexed
	private String email;

	// 职位
	@Indexed
	private String position;

	// 级别,用于排序,当position不为空的时候,需要提供rank
	@Indexed
	private String rank;

	// 显示名
	private String displayName;

	// 头像对应的附件id
	private String avatarId;

	// 如果为系统用户, 则org为空
	private UserType userType;

	private Gender gender;

	private Date birthday;

	private boolean restricted = false;

	@DBRef(lazy = true)
	private Organization organization;

	@Indexed
	@DBRef(lazy = true)
	private List<Organization> organizations;

	@JsonIgnore
	private String password;

	private boolean expired = false;

	private boolean locked = false;

	private boolean enabled = true;

	private boolean deleted = false;

	private Date deletedAt;

	@Transient
	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	private List<SysRole> roles = new ArrayList<SysRole>();

	private Date createdAt;

	private Date modifiedAt;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null) {
			username = username.trim().toLowerCase();
		}
		this.username = username;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPosition() {

		return position;
	}

	public void setPosition(String position) {
		if (position != null) {
			position = position.trim();
		}
		this.position = position;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		if (contactPhone != null) {
			contactPhone = contactPhone.trim();
		}
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null) {
			email = email.trim();
		}
		this.email = email;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		if (rank != null) {
			rank = rank.trim();
		}
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
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

	@Deprecated
	public Organization getOrganization() {
		return organization;
	}

	@Deprecated
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
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

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public void addRole(SysRole role) {
		if (this.roles.contains(role)) {
			return;
		}
		this.roles.add(role);
	}

	public void removeRole(SysRole role) {
		this.roles.remove(role);
	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !expired;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

}
