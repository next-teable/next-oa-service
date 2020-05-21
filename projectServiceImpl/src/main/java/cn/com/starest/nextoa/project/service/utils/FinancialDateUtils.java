package cn.com.starest.nextoa.project.service.utils;

import cn.com.starest.nextoa.project.service.FinancialYear;

import java.util.Calendar;

/**
 * @author dz
 */
public abstract class FinancialDateUtils {

	public static FinancialYear[] getYearsForReport() {
		int beginYear = 2015;
		int endYear = Calendar.getInstance().get(Calendar.YEAR);

		if (endYear <= beginYear) {
			return new FinancialYear[0];
		}

		FinancialYear[] result = new FinancialYear[endYear - beginYear + 1];
		for (int i = 0; i <= endYear - beginYear; i++) {
			result[i] = new FinancialYear(beginYear + i, i == (endYear - beginYear));
		}

		return result;
	}

	public static boolean isFuture(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		int yearOfNow = calendar.get(Calendar.YEAR);
		int monthOfNow = calendar.get(Calendar.MONTH) + 1;
		return (year > yearOfNow) || (year == yearOfNow && month > monthOfNow);
	}

	public static boolean isNow(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		int yearOfNow = calendar.get(Calendar.YEAR);
		int monthOfNow = calendar.get(Calendar.MONTH) + 1;
		return (year == yearOfNow && month == monthOfNow);
	}

}
