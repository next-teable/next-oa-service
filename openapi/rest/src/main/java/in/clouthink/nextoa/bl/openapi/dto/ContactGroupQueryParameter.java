package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.ContactGroupQueryRequest;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
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
