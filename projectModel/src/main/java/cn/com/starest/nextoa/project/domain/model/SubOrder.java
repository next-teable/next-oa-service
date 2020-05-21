package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 分包订单
 *
 * @author dz
 */
@Document(collection = "SubOrders")
public class SubOrder extends AbstractSubOrder implements Publishable {

	private boolean published;

	private Date publishAt;

	@Indexed
	@DBRef(lazy = true)
	private User publishBy;

	@Override
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Override
	public Date getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}

	@Override
	public User getPublishBy() {
		return publishBy;
	}

	public void setPublishBy(User publishBy) {
		this.publishBy = publishBy;
	}
}
