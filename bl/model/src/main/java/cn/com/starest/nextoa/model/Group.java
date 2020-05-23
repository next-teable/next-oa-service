package cn.com.starest.nextoa.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 分组,在某个组织机构下,可能分为多个职能组,组与组之间的员工可能有交集,例如研发中心下某个员工既可以属于技术研发组,也可以属于项目一组
 */
@Document(collection = "Groups")
public class Group extends StringIdentifier {

	@Indexed
	private String name;

	@Indexed
	@DBRef
	private Organization organization;

	@Indexed
	@DBRef
	private User createdBy;

	private Date createdAt;

	@DBRef
	private User modifiedBy;

	private Date modifiedAt;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
