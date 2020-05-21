package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Salary;

/**
 * @author dz
 */
public class SalaryDetail extends SalarySummary {

	public static void convert(Salary fromObj, SalaryDetail result) {
		SalarySummary.convert(fromObj, result);
	}

	public static SalaryDetail from(Salary fromObj) {
		if (fromObj == null) {
			return null;
		}
		SalaryDetail result = new SalaryDetail();
		convert(fromObj, result);
		return result;
	}

}
