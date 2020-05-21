package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * 项目利润分配设置
 *
 * @author dz
 */
@Document(collection = "ProjectSettlements")
public class ProjectSettlement extends BaseModel {

	public enum ProjectSettlementStatus {
		PENDING,
		COMPLETE,
	}

	@Indexed
	@DBRef(lazy = true)
	private ProjectCompletion projectCompletion;

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

	private ProjectSettlementStatus status = ProjectSettlementStatus.PENDING;

	@DBRef(lazy = true)
	private User user1;

	private BigDecimal percent1;

	private BigDecimal marketPercent1;

	@DBRef(lazy = true)
	private User user2;

	private BigDecimal percent2;

	private BigDecimal marketPercent2;

	@DBRef(lazy = true)
	private User user3;

	private BigDecimal percent3;

	private BigDecimal marketPercent3;

	@DBRef(lazy = true)
	private User user4;

	private BigDecimal percent4;

	@DBRef(lazy = true)
	private User user5;

	private BigDecimal percent5;

	public ProjectCompletion getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(ProjectCompletion projectCompletion) {
		this.projectCompletion = projectCompletion;

		if (projectCompletion != null) {
			setProject(projectCompletion.getProject());
			setContract(projectCompletion.getContract());
			setOrder(projectCompletion.getOrder());
		}
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

	public ProjectSettlementStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectSettlementStatus status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public BigDecimal getPercent1() {
		return percent1;
	}

	public void setPercent1(BigDecimal percent1) {
		this.percent1 = percent1;
	}

	public BigDecimal getMarketPercent1() {
		return marketPercent1;
	}

	public void setMarketPercent1(BigDecimal marketPercent1) {
		this.marketPercent1 = marketPercent1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public BigDecimal getPercent2() {
		return percent2;
	}

	public void setPercent2(BigDecimal percent2) {
		this.percent2 = percent2;
	}

	public BigDecimal getMarketPercent2() {
		return marketPercent2;
	}

	public void setMarketPercent2(BigDecimal marketPercent2) {
		this.marketPercent2 = marketPercent2;
	}

	public User getUser3() {
		return user3;
	}

	public void setUser3(User user3) {
		this.user3 = user3;
	}

	public BigDecimal getPercent3() {
		return percent3;
	}

	public void setPercent3(BigDecimal percent3) {
		this.percent3 = percent3;
	}

	public BigDecimal getMarketPercent3() {
		return marketPercent3;
	}

	public void setMarketPercent3(BigDecimal marketPercent3) {
		this.marketPercent3 = marketPercent3;
	}

	public User getUser4() {
		return user4;
	}

	public void setUser4(User user4) {
		this.user4 = user4;
	}

	public BigDecimal getPercent4() {
		return percent4;
	}

	public void setPercent4(BigDecimal percent4) {
		this.percent4 = percent4;
	}

	public User getUser5() {
		return user5;
	}

	public void setUser5(User user5) {
		this.user5 = user5;
	}

	public BigDecimal getPercent5() {
		return percent5;
	}

	public void setPercent5(BigDecimal percent5) {
		this.percent5 = percent5;
	}
}
