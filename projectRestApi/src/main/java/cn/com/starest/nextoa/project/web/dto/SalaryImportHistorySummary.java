package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.SalaryImportHistory;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class SalaryImportHistorySummary extends AbstractBaseSummary {

	public static void convert(SalaryImportHistory fromObj, SalaryImportHistorySummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);
	}

	public static SalaryImportHistorySummary from(SalaryImportHistory fromObj) {
		if (fromObj == null) {
			return null;
		}
		SalaryImportHistorySummary result = new SalaryImportHistorySummary();
		convert(fromObj, result);
		return result;
	}

}
