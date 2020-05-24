package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;

/**
 */
public class MessageReceiverNameQueryParameter extends PageQueryParameter {

	private String receiverName;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

}
