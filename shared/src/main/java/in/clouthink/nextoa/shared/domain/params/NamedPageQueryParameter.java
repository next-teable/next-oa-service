package in.clouthink.nextoa.shared.domain.params;

import in.clouthink.nextoa.shared.domain.request.NamedPageQueryRequest;
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
