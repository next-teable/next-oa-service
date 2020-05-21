package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Project;

/**
 * @author dz
 */
public class ProjectDetail extends ProjectSummary {

	public static ProjectDetail from(Project fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectDetail result = new ProjectDetail();
		convert(fromObj, result);
		return result;
	}

	public static void convert(Project fromObj, ProjectDetail result) {
		ProjectSummary.convert(fromObj, result);
		if (fromObj.getStartBy() != null) {
			result.setStartById(fromObj.getStartBy().getId());
			result.setStartByName(fromObj.getStartBy().getUsername());
		}
		if (fromObj.getCloseBy() != null) {
			result.setCloseById(fromObj.getCloseBy().getId());
			result.setCloseByName(fromObj.getCloseBy().getUsername());
		}
	}

	private String startById;

	private String startByName;

	private String closeById;

	private String closeByName;

	public String getStartById() {
		return startById;
	}

	public void setStartById(String startById) {
		this.startById = startById;
	}

	public String getStartByName() {
		return startByName;
	}

	public void setStartByName(String startByName) {
		this.startByName = startByName;
	}

	public String getCloseById() {
		return closeById;
	}

	public void setCloseById(String closeById) {
		this.closeById = closeById;
	}

	public String getCloseByName() {
		return closeByName;
	}

	public void setCloseByName(String closeByName) {
		this.closeByName = closeByName;
	}


}
