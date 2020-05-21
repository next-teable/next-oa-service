package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;

/**
 * @author dz
 */
public class CompanyReceivedPaymentDetail extends CompanyReceivedPaymentSummary {

	public static void convert(CompanyReceivedPayment fromObj, CompanyReceivedPaymentDetail result) {
		CompanyReceivedPaymentSummary.convert(fromObj, result);
	}

	public static CompanyReceivedPaymentDetail from(CompanyReceivedPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		CompanyReceivedPaymentDetail result = new CompanyReceivedPaymentDetail();
		convert(fromObj, result);
		return result;
	}

}
