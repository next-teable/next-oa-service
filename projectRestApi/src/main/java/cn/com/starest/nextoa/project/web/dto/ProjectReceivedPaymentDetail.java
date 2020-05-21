package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;

/**
 * @author dz
 */
public class ProjectReceivedPaymentDetail extends ProjectReceivedPaymentSummary {

	public static void convert(ProjectReceivedPayment fromObj, ProjectReceivedPaymentDetail result) {
		ProjectReceivedPaymentSummary.convert(fromObj, result);
	}

	public static ProjectReceivedPaymentDetail from(ProjectReceivedPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectReceivedPaymentDetail result = new ProjectReceivedPaymentDetail();
		convert(fromObj, result);
		return result;
	}

}
