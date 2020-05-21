package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目
 *
 * @author dz
 */
@Document(collection = "Projects")
public class Project extends AbstractProject {

	public static boolean isDraft(Project project) {
		return project.getStatus() == ProjectStatus.DRAFT;
	}

	public static boolean isCompleted(Project project) {
		return project.getStatus() == ProjectStatus.COMPLETED;
	}

	public static boolean isSettled(Project project) {
		return project.getStatus() == ProjectStatus.SETTLED;
	}

	public static boolean isRunning(Project project) {
		return project.getStatus() == ProjectStatus.RUNNING;
	}

	public static boolean isClosed(Project project) {
		return project.getStatus() == ProjectStatus.CLOSED;
	}

	//折算比例 (例如: 0.3, 0.15)
	private BigDecimal reducedPercent;

	//公司项目经理 （ 至少一个 ）
	@Indexed
	@DBRef(lazy = true)
	private List<User> managers = new ArrayList();

	private ProjectStatus status = ProjectStatus.DRAFT;

	private Date startAt;

	@Indexed
	@DBRef(lazy = true)
	private User startBy;

	@Indexed
	private Date closeAt;

	@Indexed
	@DBRef(lazy = true)
	private User closeBy;

	private Date completedAt;

	private Date settledAt;

	//用于排序
	//回款项目利润
	@Indexed
	BigDecimal currentCollectProfitAmount;

	//回款利润率
	@Indexed
	BigDecimal currentCollectProfitRate;

	public BigDecimal getReducedPercent() {
		return reducedPercent;
	}

	public void setReducedPercent(BigDecimal reducedPercent) {
		this.reducedPercent = reducedPercent;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Date getCloseAt() {
		return closeAt;
	}

	public void setCloseAt(Date closeAt) {
		this.closeAt = closeAt;
	}

	public User getCloseBy() {
		return closeBy;
	}

	public void setCloseBy(User closeBy) {
		this.closeBy = closeBy;
	}

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}

	public User getStartBy() {
		return startBy;
	}

	public void setStartBy(User startBy) {
		this.startBy = startBy;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public Date getSettledAt() {
		return settledAt;
	}

	public void setSettledAt(Date settledAt) {
		this.settledAt = settledAt;
	}

	public BigDecimal getCurrentCollectProfitAmount() {
		return currentCollectProfitAmount;
	}

	public void setCurrentCollectProfitAmount(BigDecimal currentCollectProfitAmount) {
		this.currentCollectProfitAmount = currentCollectProfitAmount;
	}

	public BigDecimal getCurrentCollectProfitRate() {
		return currentCollectProfitRate;
	}

	public void setCurrentCollectProfitRate(BigDecimal currentCollectProfitRate) {
		this.currentCollectProfitRate = currentCollectProfitRate;
	}

}
