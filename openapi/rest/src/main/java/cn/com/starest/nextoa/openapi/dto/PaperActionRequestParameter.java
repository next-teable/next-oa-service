package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.dtr.ForwardPaperRequest;
import cn.com.starest.nextoa.model.dtr.ReplyPaperRequest;

/**
 *
 */
public class PaperActionRequestParameter extends AbstractPaperRequestParameter implements ForwardPaperRequest, ReplyPaperRequest {

	private String messageId;

	private String content;

	@Override
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
