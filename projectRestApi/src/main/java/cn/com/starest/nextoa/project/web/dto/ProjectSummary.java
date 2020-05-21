package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.SimpleUser;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class ProjectSummary extends AbstractProjectDto {

	public static void convert(Project fromObj, ProjectSummary result) {
		AbstractProjectDto.convert(fromObj, result);
		if (fromObj.getManagers() != null) {
			result.setProjectManagers(fromObj.getManagers()
											 .stream()
											 .map(mgr -> SimpleUser.from(mgr))
											 .collect(Collectors.toList()));
		}
	}

	public static ProjectSummary from(Project fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectSummary result = new ProjectSummary();
		convert(fromObj, result);
		return result;
	}

	private ProjectStatus status;

	private Date startAt;

	private Date closeAt;

	//竣工
	private boolean completed;

	private Date completedAt;

	//结算
	private boolean settled;

	private Date settledAt;

	private List<SimpleUser> projectManagers = new ArrayList();

	public List<SimpleUser> getProjectManagers() {
		return projectManagers;
	}

	public void setProjectManagers(List<SimpleUser> projectManagers) {
		this.projectManagers = projectManagers;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getCloseAt() {
		return closeAt;
	}

	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getSettledAt() {
		return settledAt;
	}

	public void setSettledAt(Date settledAt) {
		this.settledAt = settledAt;
	}
}
