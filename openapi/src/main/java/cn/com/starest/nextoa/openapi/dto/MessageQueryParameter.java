package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.MessageStatus;
import cn.com.starest.nextoa.model.dtr.MessageQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class MessageQueryParameter extends DateRangedQueryParameter implements MessageQueryRequest {

	private String category;

	private String title;

	private String initiatorUsername;

	private MessageStatus messageStatus;

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInitiatorUsername() {
		return initiatorUsername;
	}

	public void setInitiatorUsername(String initiatorUsername) {
		this.initiatorUsername = initiatorUsername;
	}

	@Override
	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}
}
