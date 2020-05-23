package cn.com.starest.nextoa.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 */
public class Receiver {

	@DBRef
	private User user;

	private boolean notifyEnabled;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isNotifyEnabled() {
		return notifyEnabled;
	}

	public void setNotifyEnabled(boolean notifyEnabled) {
		this.notifyEnabled = notifyEnabled;
	}
}
