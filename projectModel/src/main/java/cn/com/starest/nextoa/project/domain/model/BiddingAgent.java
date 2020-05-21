package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 招标代理单位
 *
 * @author dz
 */
@Document(collection = "BiddingAgents")
public class BiddingAgent extends BaseModel {

	@NotEmpty(message = "名称不能为空")
	@Indexed
	private String name;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
