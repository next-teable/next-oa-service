package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 项目修改历史记录
 *
 * @author dz
 */
@Document(collection = "ProjectHistories")
public class ProjectHistory extends AbstractProject {

	@Indexed
	@DBRef(lazy = true)
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
