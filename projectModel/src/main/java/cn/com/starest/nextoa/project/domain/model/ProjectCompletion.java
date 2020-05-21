package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author dz
 */
@Document(collection = "ProjectCompletions")
public class ProjectCompletion extends BaseModel {

	public enum ProjectCompletionStatus {
		COMPLETE,
		PENDING_SETTLEMENT,
		SETTLEMENT_DONE,
	}

	//项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	@Indexed
	@DBRef(lazy = true)
	private Contract contract;

	@Indexed
	@DBRef(lazy = true)
	private Order order;

	private Date completeAt;

	private ProjectCompletionStatus status = ProjectCompletionStatus.COMPLETE;

	private String description;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCompleteAt() {
		return completeAt;
	}

	public void setCompleteAt(Date completeAt) {
		this.completeAt = completeAt;
	}

	public ProjectCompletionStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectCompletionStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
