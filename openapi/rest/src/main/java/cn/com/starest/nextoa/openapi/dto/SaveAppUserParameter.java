package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Gender;
import cn.com.starest.nextoa.model.dtr.SaveUserRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 *
 */
public class SaveAppUserParameter implements SaveUserRequest {

	private String username;

	private String password;

	private String contactPhone;

	private String officePhone;

	private String email;

	private String extensionNumber;

	private String position;

	private String rank;

	private Gender gender;

	private String avatarId;

	private boolean isRestricted;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	@Override
	public boolean isRestricted() {
		return isRestricted;
	}

	public void setRestricted(boolean restricted) {
		isRestricted = restricted;
	}
}
