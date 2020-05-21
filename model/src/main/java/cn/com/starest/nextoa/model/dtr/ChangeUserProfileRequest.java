package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.Gender;

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
