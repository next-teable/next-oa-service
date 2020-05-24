package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;

/**
 */
public class MessageTitleQueryParameter extends PageQueryParameter {

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
