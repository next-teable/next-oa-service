package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.UsernameQueryRequest;
import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class UsernamePageQueryParameter extends DateRangedQueryParameter implements UsernameQueryRequest {

	private String username;

	private String pinyin;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
}
