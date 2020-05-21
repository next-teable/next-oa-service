package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.Gender;

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
