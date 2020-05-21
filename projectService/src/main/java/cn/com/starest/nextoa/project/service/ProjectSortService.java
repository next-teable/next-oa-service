package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.project.domain.model.Project;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface ProjectSortService {


	class ProjectContractAmount {

		private Project project;

		private BigDecimal amount = BigDecimal.ZERO;

		public ProjectContractAmount(Project project, BigDecimal amount) {
			this.project = project;
			this.amount = amount;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
	}

	class ProjectSort {

		private Project project;

		private BigDecimal contractAmount;

		private BigDecimal orderAmount;

		public ProjectSort(Project project, BigDecimal contractAmount, BigDecimal orderAmount) {
			this.project = project;
			this.contractAmount = contractAmount;
			this.orderAmount = orderAmount;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public BigDecimal getContractAmount() {
			return contractAmount;
		}

		public void setContractAmount(BigDecimal contractAmount) {
			this.contractAmount = contractAmount;
		}

		public BigDecimal getOrderAmount() {
			return orderAmount;
		}

		public void setOrderAmount(BigDecimal orderAmount) {
			this.orderAmount = orderAmount;
		}
	}


	/**
	 * 项目排序规则：
	 * <p>
	 * 1、结项的项目排到最后（根据结项时间顺序排）；
	 * <p>
	 * 2、最前面排进行中的，首先根据合同金额选择优先排的地市（合同金额最大的项目归属地市排第一）
	 * ，该地市的全部项目订单金额不为0的根据合同金额由大到小排前面，订单金额为0的根据合同金额由大到小排后面
	 */
	void sortProjects();

	/**
	 * 清空排序信息
	 */
	void clearProjectSorts();
}
