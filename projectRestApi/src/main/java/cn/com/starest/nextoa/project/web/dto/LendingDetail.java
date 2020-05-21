package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Lending;

/**
 * @author dz
 */
public class LendingDetail extends LendingSummary {

	public static void convert(Lending fromObj, LendingDetail result) {
		LendingSummary.convert(fromObj, result);
	}

	public static LendingDetail from(Lending fromObj) {
		if (fromObj == null) {
			return null;
		}
		LendingDetail result = new LendingDetail();
		convert(fromObj, result);
		return result;
	}

	//借款原因
	private String reason;

	//是否结清
	private boolean settled;

	//结清日期
	private boolean settleAt;

	//如果是还款,需要有对应的借款
	private String repaymentToId;

	//备注
	private String description;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public boolean isSettleAt() {
		return settleAt;
	}

	public void setSettleAt(boolean settleAt) {
		this.settleAt = settleAt;
	}

	public String getRepaymentToId() {
		return repaymentToId;
	}

	public void setRepaymentToId(String repaymentToId) {
		this.repaymentToId = repaymentToId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
