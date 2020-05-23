package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.dtr.SaveGroupRequest;
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
