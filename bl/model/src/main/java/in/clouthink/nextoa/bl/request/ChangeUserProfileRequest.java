package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Gender;

import java.util.Date;

/**
 */
public interface ChangeUserProfileRequest extends AbstractUserRequest {

	String getOfficePhone();

	String getExtensionNumber();

	Gender getGender();

	Date getBirthday();

	//	String getAvatarId();

}
