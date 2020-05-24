package in.clouthink.nextoa.bl.model;

import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 消息状态变迁历史纪录
 */
@Document(collection = "MessageTransitions")
public class MessageTransition extends StringIdentifier {

	private String bizActionRefId;

	@Indexed
	@DBRef
	private Message message;

	private MessageStatus fromStatus;

	private MessageStatus toStatus;

	@DBRef(lazy = true)
	private User transitedBy;

	private Date transitedAt;

	public String getBizActionRefId() {
		return bizActionRefId;
	}

	public void setBizActionRefId(String bizActionRefId) {
		this.bizActionRefId = bizActionRefId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public MessageStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(MessageStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	public MessageStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(MessageStatus toStatus) {
		this.toStatus = toStatus;
	}

	public User getTransitedBy() {
		return transitedBy;
	}

	public void setTransitedBy(User transitedBy) {
		this.transitedBy = transitedBy;
	}

	public Date getTransitedAt() {
		return transitedAt;
	}

	public void setTransitedAt(Date transitedAt) {
		this.transitedAt = transitedAt;
	}
}
