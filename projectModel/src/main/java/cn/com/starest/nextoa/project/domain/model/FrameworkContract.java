package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * 框架合同
 *
 * @author dz
 */
@Document(collection = "FrameworkContracts")
public class FrameworkContract extends BaseModel {

	@NotEmpty(message = "名称不能为空")
	@Indexed
	private String name;

	@NotNull(message = "类别不能为空")
	@Indexed
	private FrameworkContractType type;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FrameworkContractType getType() {
		return type;
	}

	public void setType(FrameworkContractType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
