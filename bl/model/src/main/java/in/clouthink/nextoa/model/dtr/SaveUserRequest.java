package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.Gender;

import java.util.Date;

/**
 */
public interface SaveUserRequest extends AbstractUserRequest {

	String getUsername();

	String getOfficePhone();

	String getExtensionNumber();

	Gender getGender();

	Date getBirthday();

	String getPassword();

	String getPosition();

	String getRank();

	String getAvatarId();

	boolean isRestricted();

}
