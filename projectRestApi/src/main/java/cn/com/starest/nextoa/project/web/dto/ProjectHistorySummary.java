package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ProjectHistory;

import java.util.Date;

/**
 * @author dz
 */
public class ProjectHistorySummary extends ProjectSummary {

	public static void convert(ProjectHistory fromObj, ProjectHistorySummary result) {
		ProjectSummary.convert(fromObj, result);
		if (fromObj.getModifiedBy() != null) {
			result.setModifiedById(fromObj.getModifiedBy().getId());
			result.setModifiedByName(fromObj.getModifiedBy().getUsername());
		}
	}

	public static ProjectHistorySummary from(ProjectHistory fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectHistorySummary result = new ProjectHistorySummary();
		convert(fromObj, result);
		return result;
	}

	private Date modifiedAt;

	private String modifiedById;

	private String modifiedByName;

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
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
}
