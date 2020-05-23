package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.dtr.SaveGroupRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveGroupParameter implements SaveGroupRequest {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
