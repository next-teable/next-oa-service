package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 订单
 *
 * @author dz
 */
@Document(collection = "Orders")
public class Order extends AbstractOrder implements Publishable {

	public static String getOrderName(Order order) {
		if (order == null) {
			return null;
		}

		return order.isSettled() ? "[已结算]" + order.getName() : order.getName();
	}

	private boolean published;

	private Date publishAt;

	@Indexed
	@DBRef(lazy = true)
	private User publishBy;

	//竣工
	@Indexed
	private boolean completed;

	private Date completedAt;

	@DBRef(lazy = true)
	private User completedBy;

	//结算
	@Indexed
	private boolean settled;

	private Date settledAt;

	@DBRef(lazy = true)
	private User settledBy;

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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public User getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(User completedBy) {
		this.completedBy = completedBy;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getSettledAt() {
		return settledAt;
	}

	public void setSettledAt(Date settledAt) {
		this.settledAt = settledAt;
	}

	public User getSettledBy() {
		return settledBy;
	}

	public void setSettledBy(User settledBy) {
		this.settledBy = settledBy;
	}
}
