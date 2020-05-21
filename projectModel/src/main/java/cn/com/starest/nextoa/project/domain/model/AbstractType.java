package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

/**
 * 各种类型定义的抽象
 *
 * @author dz
 */
public class AbstractType extends StringIdentifier {

	@NotEmpty(message = "名称不能为空")
	private String name;

	private String description;

	@DBRef(lazy = true)
	private User modifiedBy;

	private Date modifiedAt;

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

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
