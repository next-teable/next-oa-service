package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectReceivedPaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectReceivedPaymentService {

	/**
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param item
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(ProjectReceivedPayment item, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPayment createProjectReceivedPayment(SaveProjectReceivedPaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPayment updateProjectReceivedPayment(String id,
														SaveProjectReceivedPaymentRequest request,
														User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	ProjectReceivedPayment findProjectReceivedPaymentById(String id, User byWho);

	/**
	 * @param issueInvoice
	 * @return
	 */
	List<ProjectReceivedPayment> findListByIssueInvoice(IssueInvoice issueInvoice);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteProjectReceivedPaymentById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<ProjectReceivedPayment> listProjectReceivedPayments(ProjectReceivedPaymentQueryRequest request, User byWho);

	/**
	 * @param projectReceivedPayment
	 * @param company
	 * @param project
	 */
	void updateProjectReceivedPayment(ProjectReceivedPayment projectReceivedPayment, Company company, Project project);
}
