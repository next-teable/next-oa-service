package in.clouthink.nextoa.bl.openapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import in.clouthink.nextoa.bl.model.Gender;
import in.clouthink.nextoa.bl.request.ChangeUserProfileRequest;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 *
 */
@ApiModel
public class ChangeMyProfileParameter implements ChangeUserProfileRequest {

	private String contactPhone;

	private String officePhone;

	private String email;

	private String extensionNumber;

	private Gender gender;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

//	private String avatarId;

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

//	public String getAvatarId() {
//		return avatarId;
//	}
//
//	public void setAvatarId(String avatarId) {
//		this.avatarId = avatarId;
//	}
}
