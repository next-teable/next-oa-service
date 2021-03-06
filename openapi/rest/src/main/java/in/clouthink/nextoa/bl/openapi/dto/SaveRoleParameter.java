package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.SaveRoleRequest;

public class SaveRoleParameter implements SaveRoleRequest {

	private String code;

	private String name;

	private String description;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			name = name.trim();
		}
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code != null) {
			code = code.trim().toUpperCase();
		}
		this.code = code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
