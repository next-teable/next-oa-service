package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveProjectReceivedPaymentRequest;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ProjectReceivedPaymentRestSupport {

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPaymentSummary createProjectReceivedPayment(SaveProjectReceivedPaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPaymentSummary updateProjectReceivedPayment(String id,
															   SaveProjectReceivedPaymentRequest request,
															   User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPaymentDetail findProjectReceivedPaymentById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(ProjectReceivedPaymentQueryParameter request,
																	User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deleteProjectReceivedPaymentById(String id, User user);

}
