package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.UserType;
import cn.com.starest.nextoa.model.dtr.UserQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class UserQueryParameter extends DateRangedQueryParameter implements UserQueryRequest {

	private String username;

	private String pinyin;

	// 联系电话
	private String contactPhone;

	//办公电话
	private String officePhone;

	//电子邮箱
	private String email;

	//职位
	private String position;

	private String organizationId;

	private Boolean enabled;

	private UserType userType;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
