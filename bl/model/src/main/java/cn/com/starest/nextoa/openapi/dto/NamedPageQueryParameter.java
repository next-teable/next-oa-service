package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class NamedPageQueryParameter extends PageQueryParameter implements NamedPageQueryRequest {

	public NamedPageQueryParameter() {
	}

	public NamedPageQueryParameter(int start, int limit) {
		super(start, limit);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
