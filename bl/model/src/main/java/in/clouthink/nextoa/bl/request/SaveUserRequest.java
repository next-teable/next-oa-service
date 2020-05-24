package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Gender;

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
