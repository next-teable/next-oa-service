package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Deposit;

/**
 * @author dz
 */
public class DepositDetail extends DepositSummary {

	public static void convert(Deposit fromObj, DepositDetail result) {
		DepositSummary.convert(fromObj, result);
	}

	public static DepositDetail from(Deposit fromObj) {
		if (fromObj == null) {
			return null;
		}
		DepositDetail result = new DepositDetail();
		convert(fromObj, result);
		return result;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
