package in.clouthink.nextoa.bl.openapi.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ForwardPaperParameter extends AbstractPaperParameter {

	private String messageId;

	private String content;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
