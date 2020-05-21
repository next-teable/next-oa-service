package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.PendingPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePendingPaymentRequest;
import cn.com.starest.nextoa.project.domain.rule.PendingPaymentReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.PendingPaymentException;
import cn.com.starest.nextoa.project.exception.PendingPaymentNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectReceivedPaymentNotFoundException;
import cn.com.starest.nextoa.project.repository.CompanyReceivedPaymentRepository;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import cn.com.starest.nextoa.project.repository.ProjectReceivedPaymentRepository;
import cn.com.starest.nextoa.project.repository.SubContractorRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.PendingPaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class PendingPaymentServiceImpl implements PendingPaymentService {

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	private CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

	@Autowired
	private SubContractorRepository subContractorRepository;

	@Autowired
	private PendingPaymentRepository paymentRepository;

	@Autowired(required = false)
	private List<PendingPaymentReference> paymentReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.PENDING_PAYMENT, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(PendingPayment item, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.PENDING_PAYMENT,
																								 byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);


		return grantedActions;
	}

	@Override
	public PendingPayment createPendingPayment(SavePendingPaymentRequest request, User byWho) {
		PendingPayment pendingPayment = new PendingPayment();

		BeanUtils.copyProperties(request, pendingPayment);
		handlePendingPaymentReference(request, pendingPayment);
		PendingPayment.onCreate(pendingPayment, byWho);

		return paymentRepository.save(pendingPayment);
	}

	@Override
	public PendingPaymentAggregation aggregatePendingPayments(PendingPaymentQueryRequest request, User byWho) {
		PendingPaymentAggregation result = new PendingPaymentAggregation();
		result.setTotalAmount(paymentRepository.calculateTotalAmount(request));
		result.setTotalPayedAmount(paymentRepository.calculateTotalPayedAmount(request));
		result.setTotalPendingAmount(paymentRepository.calculateTotalPendingAmount(request));
		return result;
	}

	@Override
	public PendingPayment updatePendingPayment(String paymentId, SavePendingPaymentRequest request, User byWho) {
		PendingPayment pendingPayment = paymentRepository.findById(paymentId);
		if (pendingPayment == null) {
			throw new PendingPaymentNotFoundException("没有找到对应的待付款.");
		}

		BeanUtils.copyProperties(request, pendingPayment);
		handlePendingPaymentReference(request, pendingPayment);
		PendingPayment.onModify(pendingPayment, byWho);

		return paymentRepository.save(pendingPayment);
	}

	@Override
	public PendingPayment findPendingPaymentById(String paymentId, User byWho) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public void deletePendingPaymentById(String paymentId, User byWho) {
		PendingPayment pendingPayment = paymentRepository.findById(paymentId);
		if (pendingPayment == null) {
			throw new PendingPaymentNotFoundException("没有找到对应的待付款.");
		}

		if (pendingPayment.isPayed()) {
			throw new PendingPaymentNotFoundException("不能删除已付款的数据.");
		}

		if (paymentReferenceList != null) {
			paymentReferenceList.forEach(ref -> {
				if (ref.hasReference(pendingPayment)) {
					throw new PendingPaymentException("该待付款已被其他业务数据引用,不能删除.");
				}
			});
		}

		paymentRepository.delete(pendingPayment);
	}

	@Override
	public Page<PendingPayment> listPendingPayments(PendingPaymentQueryRequest request, User byWho) {
		return paymentRepository.queryPage(request);
	}

	private void handlePendingPaymentReference(SavePendingPaymentRequest request, PendingPayment pendingPayment) {
		if (request.getReceivedPaymentSource() == ReceivedPaymentSource.PROJECT) {
			ProjectReceivedPayment projectReceivedPayment = projectReceivedPaymentRepository.findFirstByCode(request.getReceivedPaymentCode());
			if (projectReceivedPayment == null) {
				throw new ProjectReceivedPaymentNotFoundException("无效的项目回款流程ID");
			}

			pendingPayment.setCompany(projectReceivedPayment.getCompany());
			pendingPayment.setProject(projectReceivedPayment.getProject());
			pendingPayment.setProjectReceivedPayment(projectReceivedPayment);
			pendingPayment.setPendingAt(projectReceivedPayment.getReceivedAt());

			pendingPayment.setCompanyReceivedPayment(null);
			pendingPayment.setBizDepartment(null);
		}

		if (request.getReceivedPaymentSource() == ReceivedPaymentSource.COMPANY) {
			CompanyReceivedPayment companyReceivedPayment = companyReceivedPaymentRepository.findFirstByCode(request.getReceivedPaymentCode());
			if (companyReceivedPayment == null) {
				throw new ProjectReceivedPaymentNotFoundException("无效的公司回款流程ID");
			}

			pendingPayment.setCompanyReceivedPayment(companyReceivedPayment);
			pendingPayment.setCompany(companyReceivedPayment.getCompany());
			pendingPayment.setBizDepartment(companyReceivedPayment.getBizDepartment());
			pendingPayment.setPendingAt(companyReceivedPayment.getReceivedAt());

			pendingPayment.setProjectReceivedPayment(null);
			pendingPayment.setProject(null);
		}

		SubContractor subContractor = subContractorRepository.findById(request.getSubContractorId());
		if (subContractor == null) {
			throw new EntityNotFoundException("无效的待付款单位id");
		}
		pendingPayment.setSubContractor(subContractor);
	}

}
