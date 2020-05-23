package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.dtr.ContactGroupQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class ContactGroupQueryParameter extends PageQueryParameter implements ContactGroupQueryRequest {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
