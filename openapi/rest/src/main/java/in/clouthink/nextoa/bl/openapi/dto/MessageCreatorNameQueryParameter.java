package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;

/**
 */
public class MessageCreatorNameQueryParameter extends PageQueryParameter {

	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
