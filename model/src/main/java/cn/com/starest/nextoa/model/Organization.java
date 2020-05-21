package cn.com.starest.nextoa.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 组织机构,支持树结构
 */
@Document(collection = "Organizations")
public class Organization extends StringIdentifier {

	@Indexed
	@DBRef
	private Organization parent;

	private boolean leaf;

	//编码,用于排序,可为空,但是不能重复
	@Indexed
	private String code;

	@Indexed
	private String name;

	@DBRef
	private User createdBy;

	private Date createdAt;

	@DBRef
	private User modifiedBy;

	private Date modifiedAt;

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		parent.leaf = true;
		this.parent = parent;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
