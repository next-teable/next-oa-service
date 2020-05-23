package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.User;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class SimpleUser {

	static void convert(User user, SimpleUser result) {
		BeanUtils.copyProperties(user, result);
	}

	public static SimpleUser from(User user) {
		if (user == null) {
			return null;
		}
		SimpleUser result = new SimpleUser();
		convert(user, result);
		return result;
	}

	private String id;

	private String username;

	private String avatarId;

	private String contactPhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(String avatarId) {
		this.avatarId = avatarId;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
