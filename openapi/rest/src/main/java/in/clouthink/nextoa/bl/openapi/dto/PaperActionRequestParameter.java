package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.ForwardPaperRequest;
import in.clouthink.nextoa.bl.request.ReplyPaperRequest;

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
