package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.Gender;

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
