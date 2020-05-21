package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveDictionaryRequest;

import javax.validation.constraints.NotNull;

/**
 * @author dz
 */
public class SaveDictionaryParameter implements SaveDictionaryRequest {

	@NotNull(message = "名称不能为空")
	private String name;

	private String description;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
