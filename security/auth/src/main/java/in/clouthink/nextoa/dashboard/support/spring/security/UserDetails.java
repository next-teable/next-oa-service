package in.clouthink.nextoa.dashboard.support.spring.security;

import org.springframework.security.core.userdetails.User;

/**
 *
 */
public class UserDetails extends User {

	private in.clouthink.nextoa.model.User user;

	public UserDetails(in.clouthink.nextoa.model.User user) {
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

	public in.clouthink.nextoa.model.User getUser() {
		return user;
	}

}
