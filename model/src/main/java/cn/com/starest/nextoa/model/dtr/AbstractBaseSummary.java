package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.shared.StringIdentifier;

import java.util.Date;

/**
 * @author dz
 */
public class AbstractBaseSummary extends StringIdentifier {

	public static void convert(BaseModel baseModel, AbstractBaseSummary result) {
		if (baseModel == null) {
			return;
		}

		if (baseModel.getCreatedBy() != null) {
			result.setCreatedById(baseModel.getCreatedBy().getId());
			result.setCreatedByName(baseModel.getCreatedBy().getUsername());
		}
		result.setCreatedAt(baseModel.getCreatedAt());

		if (baseModel.getModifiedBy() != null) {
			result.setModifiedById(baseModel.getModifiedBy().getId());
			result.setModifiedByName(baseModel.getModifiedBy().getUsername());
		}
		result.setModifiedAt(baseModel.getModifiedAt());
	}

	private String createdById;
	private String createdByName;
	private Date createdAt;

	private String modifiedById;
	private String modifiedByName;
	private Date modifiedAt;

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(String modifiedById) {
		this.modifiedById = modifiedById;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
