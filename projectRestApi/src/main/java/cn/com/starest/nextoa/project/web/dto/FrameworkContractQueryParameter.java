package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.NamedPageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.FrameworkContractType;
import cn.com.starest.nextoa.project.domain.request.FrameworkContractQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 * @author dz
 */
@ApiModel
public class FrameworkContractQueryParameter extends NamedPageQueryParameter implements FrameworkContractQueryRequest {

	private FrameworkContractType type;

	public FrameworkContractType getType() {
		return type;
	}

	public void setType(FrameworkContractType type) {
		this.type = type;
	}
}
