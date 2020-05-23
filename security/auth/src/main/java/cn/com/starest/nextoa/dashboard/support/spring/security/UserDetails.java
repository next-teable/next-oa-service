package cn.com.starest.nextoa.dashboard.support.spring.security;

import org.springframework.security.core.userdetails.User;

/**
 *
 */
public class UserDetails extends User {

	private cn.com.starest.nextoa.model.User user;

	public UserDetails(cn.com.starest.nextoa.model.User user) {
		super(user.getUsername(),
			  user.getPassword(),
			  user.isEnabled(),
			  !user.isExpired(),
			  !user.isExpired(),
			  !user.isLocked(),
			  user.getAuthorities());
		this.user = user;
	}

	public String getUserId() {
		return user.getId();
	}

	public cn.com.starest.nextoa.model.User getUser() {
		return user;
	}

}
