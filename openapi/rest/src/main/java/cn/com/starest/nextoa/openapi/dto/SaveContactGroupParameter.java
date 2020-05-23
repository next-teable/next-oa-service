package cn.com.starest.nextoa.openapi.dto;

import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveContactGroupParameter {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
