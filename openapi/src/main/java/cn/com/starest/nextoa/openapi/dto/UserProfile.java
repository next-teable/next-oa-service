package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.User;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class UserProfile extends UserSummary {

	public static UserProfile from(User user) {
		if (user == null) {
			return null;
		}
		UserProfile result = new UserProfile();
		convert(user, result);
		return result;
	}

	private String avatarId;

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}
}
