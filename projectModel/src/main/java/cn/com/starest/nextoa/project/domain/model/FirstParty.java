package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 甲方
 *
 * @author dz
 */
@Document(collection = "FirstParties")
public class FirstParty extends BaseModel {

	@NotEmpty(message = "名称不能为空")
	@Indexed
	private String name;

	@NotEmpty(message = "省不能为空")
	private String province;

	@NotEmpty(message = "市不能为空")
	private String city;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
