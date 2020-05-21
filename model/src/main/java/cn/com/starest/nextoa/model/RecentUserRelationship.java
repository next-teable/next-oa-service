package cn.com.starest.nextoa.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "RecentUserRelationships")
public class RecentUserRelationship extends StringIdentifier {

	@Indexed
	@DBRef
	private User user;

	@DBRef
	private User recentUser;

	private Date createdAt;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getRecentUser() {
		return recentUser;
	}

	public void setRecentUser(User recentUser) {
		this.recentUser = recentUser;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
