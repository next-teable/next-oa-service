package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveProjectSettlementRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveProjectSettlementParameter implements SaveProjectSettlementRequest {

	@NotNull(message = "领导1不能为空")
	private String user1Id;

	@DecimalMin(value = "0", inclusive = true, message = "领导1提成比例不能小于零")
	private BigDecimal percent1;

	@DecimalMin(value = "0", inclusive = true, message = "领导1市场提成比例不能小于零")
	private BigDecimal marketPercent1;

	@NotNull(message = "领导2不能为空")
	private String user2Id;

	@DecimalMin(value = "0", inclusive = true, message = "领导2提成比例不能小于零")
	private BigDecimal percent2;

	@DecimalMin(value = "0", inclusive = true, message = "领导2市场提成比例不能小于零")
	private BigDecimal marketPercent2;

	@NotNull(message = "领导3不能为空")
	private String user3Id;

	@DecimalMin(value = "0", inclusive = true, message = "领导3提成比例不能小于零")
	private BigDecimal percent3;

	@DecimalMin(value = "0", inclusive = true, message = "领导3市场提成比例不能小于零")
	private BigDecimal marketPercent3;

	@NotNull(message = "市场部不能为空")
	private String user4Id;

	@DecimalMin(value = "0", inclusive = true, message = "市场部提成比例不能小于零")
	private BigDecimal percent4;

	@NotNull(message = "项目经理不能为空")
	private String user5Id;

	@DecimalMin(value = "0", inclusive = true, message = "项目经理提成比例不能小于零")
	private BigDecimal percent5;

	@Override
	public String getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
	}

	@Override
	public BigDecimal getPercent1() {
		return percent1;
	}

	public void setPercent1(BigDecimal percent1) {
		this.percent1 = percent1;
	}

	@Override
	public BigDecimal getMarketPercent1() {
		return marketPercent1;
	}

	public void setMarketPercent1(BigDecimal marketPercent1) {
		this.marketPercent1 = marketPercent1;
	}

	@Override
	public String getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(String user2Id) {
		this.user2Id = user2Id;
	}

	@Override
	public BigDecimal getPercent2() {
		return percent2;
	}

	public void setPercent2(BigDecimal percent2) {
		this.percent2 = percent2;
	}

	@Override
	public BigDecimal getMarketPercent2() {
		return marketPercent2;
	}

	public void setMarketPercent2(BigDecimal marketPercent2) {
		this.marketPercent2 = marketPercent2;
	}

	@Override
	public String getUser3Id() {
		return user3Id;
	}

	public void setUser3Id(String user3Id) {
		this.user3Id = user3Id;
	}

	@Override
	public BigDecimal getPercent3() {
		return percent3;
	}

	public void setPercent3(BigDecimal percent3) {
		this.percent3 = percent3;
	}

	@Override
	public BigDecimal getMarketPercent3() {
		return marketPercent3;
	}

	public void setMarketPercent3(BigDecimal marketPercent3) {
		this.marketPercent3 = marketPercent3;
	}

	@Override
	public String getUser4Id() {
		return user4Id;
	}

	public void setUser4Id(String user4Id) {
		this.user4Id = user4Id;
	}

	@Override
	public BigDecimal getPercent4() {
		return percent4;
	}

	public void setPercent4(BigDecimal percent4) {
		this.percent4 = percent4;
	}

	@Override
	public String getUser5Id() {
		return user5Id;
	}

	public void setUser5Id(String user5Id) {
		this.user5Id = user5Id;
	}

	@Override
	public BigDecimal getPercent5() {
		return percent5;
	}

	public void setPercent5(BigDecimal percent5) {
		this.percent5 = percent5;
	}
}
