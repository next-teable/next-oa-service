package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.User;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class UserDetail extends UserSummary {

	public static UserDetail from(User user) {
		if (user == null) {
			return null;
		}
		UserDetail result = new UserDetail();
		convert(user, result);
		//TODO add avatar
		return result;
	}

	private Avatar avatar;

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}
