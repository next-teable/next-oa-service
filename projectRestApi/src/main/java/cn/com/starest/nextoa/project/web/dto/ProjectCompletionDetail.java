package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;

/**
 * @author dz
 */
public class ProjectCompletionDetail extends ProjectCompletionSummary {

	public static void convert(ProjectCompletion fromObj, ProjectCompletionDetail result) {
		ProjectCompletionSummary.convert(fromObj, result);
	}

	public static ProjectCompletionDetail from(ProjectCompletion fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectCompletionDetail result = new ProjectCompletionDetail();
		convert(fromObj, result);
		return result;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
