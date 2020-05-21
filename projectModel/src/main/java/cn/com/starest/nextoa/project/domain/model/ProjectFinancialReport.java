package cn.com.starest.nextoa.project.domain.model;

import java.util.List;

/**
 * @author dz
 */
public class ProjectFinancialReport  {

	private String companyId;

	private String companyName;

	private String projectId;

	private String projectName;

	private String projectLocation;

	private List<ProjectFinancialAggregation> items;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public List<ProjectFinancialAggregation> getItems() {
		return items;
	}

	public void setItems(List<ProjectFinancialAggregation> items) {
		this.items = items;
	}
}
