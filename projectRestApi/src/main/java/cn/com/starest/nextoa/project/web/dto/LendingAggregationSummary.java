package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class LendingAggregationSummary extends StringIdentifier {

	public static void convert(LendingAggregation fromObj, LendingAggregationSummary result) {
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getLendingBy() != null) {
			result.setLendingById(fromObj.getLendingBy().getId());
			result.setLendingByName(fromObj.getLendingBy().getUsername());
		}
		if (fromObj.getLendingTo() != null) {
			result.setLendingToId(fromObj.getLendingTo().getId());
			result.setLendingToName(fromObj.getLendingTo().getName());
		}
	}

	public static LendingAggregationSummary from(LendingAggregation fromObj) {
		if (fromObj == null) {
			return null;
		}
		LendingAggregationSummary result = new LendingAggregationSummary();
		convert(fromObj, result);
		return result;
	}

	//借款对象
	private LendingObject lendingObject;

	//借款人
	private String lendingById;

	private String lendingByName;

	//借款公司 - 分包公司
	private String lendingToId;

	private String lendingToName;

	//借款金额
	private BigDecimal lendingAmount;

	//还款金额
	private BigDecimal repaymentAmount;

	//欠款金额
	private BigDecimal owedAmount;

	//借款日期
	private Date modifiedAt;

	public LendingObject getLendingObject() {
		return lendingObject;
	}

	public void setLendingObject(LendingObject lendingObject) {
		this.lendingObject = lendingObject;
	}

	public String getLendingById() {
		return lendingById;
	}

	public void setLendingById(String lendingById) {
		this.lendingById = lendingById;
	}

	public String getLendingByName() {
		return lendingByName;
	}

	public void setLendingByName(String lendingByName) {
		this.lendingByName = lendingByName;
	}

	public String getLendingToId() {
		return lendingToId;
	}

	public void setLendingToId(String lendingToId) {
		this.lendingToId = lendingToId;
	}

	public String getLendingToName() {
		return lendingToName;
	}

	public void setLendingToName(String lendingToName) {
		this.lendingToName = lendingToName;
	}

	public BigDecimal getLendingAmount() {
		return lendingAmount;
	}

	public void setLendingAmount(BigDecimal lendingAmount) {
		this.lendingAmount = lendingAmount;
	}

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public BigDecimal getOwedAmount() {
		return owedAmount;
	}

	public void setOwedAmount(BigDecimal owedAmount) {
		this.owedAmount = owedAmount;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
