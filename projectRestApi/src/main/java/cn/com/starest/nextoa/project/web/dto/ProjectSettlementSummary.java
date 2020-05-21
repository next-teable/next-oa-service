package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlementAggregation;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class ProjectSettlementSummary extends AbstractPermissionAware {

	public static void convert(ProjectSettlement fromObj, ProjectSettlementSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}

		if (fromObj.getContract() != null) {
			result.setContractId(fromObj.getContract().getId());
			result.setContractName(Contract.getContractName(fromObj.getContract()));
		}

		if (fromObj.getOrder() != null) {
			result.setOrderId(fromObj.getOrder().getId());
			result.setOrderName(Order.getOrderName(fromObj.getOrder()));
		}

		if (fromObj.getUser1() != null) {
			result.setUser1Id(fromObj.getUser1().getId());
			result.setUser1Name(fromObj.getUser1().getUsername());
		}

		if (fromObj.getUser2() != null) {
			result.setUser2Id(fromObj.getUser2().getId());
			result.setUser2Name(fromObj.getUser2().getUsername());
		}

		if (fromObj.getUser3() != null) {
			result.setUser3Id(fromObj.getUser3().getId());
			result.setUser3Name(fromObj.getUser3().getUsername());
		}

		if (fromObj.getUser4() != null) {
			result.setUser4Id(fromObj.getUser4().getId());
			result.setUser4Name(fromObj.getUser4().getUsername());
		}

		if (fromObj.getUser5() != null) {
			result.setUser5Id(fromObj.getUser5().getId());
			result.setUser5Name(fromObj.getUser5().getUsername());
		}

	}

	public static ProjectSettlementSummary from(ProjectSettlement fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectSettlementSummary result = new ProjectSettlementSummary();
		convert(fromObj, result);
		return result;
	}


	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//主合同*
	private String contractId;

	private String contractName;

	//主订单
	private String orderId;

	private String orderName;

	private String user1Id;

	private String user1Name;

	private BigDecimal percent1;

	private BigDecimal marketPercent1;

	private String user2Id;

	private String user2Name;

	private BigDecimal percent2;

	private BigDecimal marketPercent2;

	private String user3Id;

	private String user3Name;

	private BigDecimal percent3;

	private BigDecimal marketPercent3;

	private String user4Id;

	private String user4Name;

	private BigDecimal percent4;

	private String user5Id;

	private String user5Name;

	private BigDecimal percent5;

	private ProjectSettlementAggregation aggregation;

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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}

	public String getUser1Name() {
		return user1Name;
	}

	public void setUser1Name(String user1Name) {
		this.user1Name = user1Name;
	}

	public BigDecimal getPercent1() {
		return percent1;
	}

	public void setPercent1(BigDecimal percent1) {
		this.percent1 = percent1;
	}

	public String getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

	public String getUser2Name() {
		return user2Name;
	}

	public void setUser2Name(String user2Name) {
		this.user2Name = user2Name;
	}

	public BigDecimal getPercent2() {
		return percent2;
	}

	public void setPercent2(BigDecimal percent2) {
		this.percent2 = percent2;
	}

	public String getUser3Id() {
		return user3Id;
	}

	public void setUser3Id(String user3Id) {
		this.user3Id = user3Id;
	}

	public String getUser3Name() {
		return user3Name;
	}

	public void setUser3Name(String user3Name) {
		this.user3Name = user3Name;
	}

	public BigDecimal getPercent3() {
		return percent3;
	}

	public void setPercent3(BigDecimal percent3) {
		this.percent3 = percent3;
	}

	public String getUser4Id() {
		return user4Id;
	}

	public void setUser4Id(String user4Id) {
		this.user4Id = user4Id;
	}

	public String getUser4Name() {
		return user4Name;
	}

	public void setUser4Name(String user4Name) {
		this.user4Name = user4Name;
	}

	public BigDecimal getPercent4() {
		return percent4;
	}

	public void setPercent4(BigDecimal percent4) {
		this.percent4 = percent4;
	}

	public String getUser5Id() {
		return user5Id;
	}

	public void setUser5Id(String user5Id) {
		this.user5Id = user5Id;
	}

	public String getUser5Name() {
		return user5Name;
	}

	public void setUser5Name(String user5Name) {
		this.user5Name = user5Name;
	}

	public BigDecimal getPercent5() {
		return percent5;
	}

	public void setPercent5(BigDecimal percent5) {
		this.percent5 = percent5;
	}

	public BigDecimal getMarketPercent1() {
		return marketPercent1;
	}

	public void setMarketPercent1(BigDecimal marketPercent1) {
		this.marketPercent1 = marketPercent1;
	}

	public BigDecimal getMarketPercent2() {
		return marketPercent2;
	}

	public void setMarketPercent2(BigDecimal marketPercent2) {
		this.marketPercent2 = marketPercent2;
	}

	public BigDecimal getMarketPercent3() {
		return marketPercent3;
	}

	public void setMarketPercent3(BigDecimal marketPercent3) {
		this.marketPercent3 = marketPercent3;
	}

	public ProjectSettlementAggregation getAggregation() {
		return aggregation;
	}

	public void setAggregation(ProjectSettlementAggregation aggregation) {
		this.aggregation = aggregation;

		if (this.aggregation.getPercent1() == null) {
			this.aggregation.setPercent1(BigDecimal.ZERO);
		}
		if (this.aggregation.getPercent2() == null) {
			this.aggregation.setPercent2(BigDecimal.ZERO);
		}
		if (this.aggregation.getPercent3() == null) {
			this.aggregation.setPercent3(BigDecimal.ZERO);
		}
		if (this.aggregation.getPercent4() == null) {
			this.aggregation.setPercent4(BigDecimal.ZERO);
		}
		if (this.aggregation.getPercent5() == null) {
			this.aggregation.setPercent5(BigDecimal.ZERO);
		}

		if (this.aggregation.getMarketPercent1() == null) {
			this.aggregation.setMarketPercent1(BigDecimal.ZERO);
		}
		if (this.aggregation.getMarketPercent2() == null) {
			this.aggregation.setMarketPercent2(BigDecimal.ZERO);
		}
		if (this.aggregation.getMarketPercent3() == null) {
			this.aggregation.setMarketPercent3(BigDecimal.ZERO);
		}

		//要求不显示小数点
		this.aggregation.setProfit1(this.aggregation.getProfit1().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setProfit2(this.aggregation.getProfit2().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setProfit3(this.aggregation.getProfit3().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setProfit4(this.aggregation.getProfit4().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setProfit5(this.aggregation.getProfit5().setScale(0, BigDecimal.ROUND_HALF_UP));

		this.aggregation.setWithdrawAmount1(this.aggregation.getWithdrawAmount1()
															.setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setWithdrawAmount2(this.aggregation.getWithdrawAmount2()
															.setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setWithdrawAmount3(this.aggregation.getWithdrawAmount3()
															.setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setWithdrawAmount4(this.aggregation.getWithdrawAmount4()
															.setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setWithdrawAmount5(this.aggregation.getWithdrawAmount5()
															.setScale(0, BigDecimal.ROUND_HALF_UP));

		this.aggregation.setMarketProfit1(this.aggregation.getMarketProfit1().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setMarketProfit2(this.aggregation.getMarketProfit2().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setMarketProfit3(this.aggregation.getMarketProfit3().setScale(0, BigDecimal.ROUND_HALF_UP));

		this.aggregation.setLeftAmount1(this.aggregation.getLeftAmount1().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setLeftAmount2(this.aggregation.getLeftAmount2().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setLeftAmount3(this.aggregation.getLeftAmount3().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setLeftAmount4(this.aggregation.getLeftAmount4().setScale(0, BigDecimal.ROUND_HALF_UP));
		this.aggregation.setLeftAmount5(this.aggregation.getLeftAmount5().setScale(0, BigDecimal.ROUND_HALF_UP));

	}
}
