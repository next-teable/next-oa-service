package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ReceiveInvoiceQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveReceiveInvoiceRequest;
import cn.com.starest.nextoa.project.domain.rule.ReceiveInvoiceReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectNotFoundException;
import cn.com.starest.nextoa.project.exception.ReceiveInvoiceException;
import cn.com.starest.nextoa.project.exception.ReceiveInvoiceNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ReceiveInvoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
@Service
public class ReceiveInvoiceServiceImpl implements ReceiveInvoiceService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private SubContractorRepository subContractorRepository;

	@Autowired(required = false)
	private List<ReceiveInvoiceReference> receiveInvoiceReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private ReceiveInvoiceRepository receiveInvoiceRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.RECEIVE_INVOICE, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(ReceiveInvoice receiveInvoice, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.RECEIVE_INVOICE,
																								 byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);

		return grantedActions;
	}

	@Override
	public ReceiveInvoice createReceiveInvoice(SaveReceiveInvoiceRequest request, User byWho) {
		ReceiveInvoice receiveInvoice = new ReceiveInvoice();

		BeanUtils.copyProperties(request, receiveInvoice);
		handleReceiveInvoiceReference(request, receiveInvoice);
		ReceiveInvoice.onCreate(receiveInvoice, byWho);

		return receiveInvoiceRepository.save(receiveInvoice);
	}

	@Override
	public ReceiveInvoice updateReceiveInvoice(String id, SaveReceiveInvoiceRequest request, User byWho) {
		ReceiveInvoice receiveInvoice = receiveInvoiceRepository.findById(id);
		if (receiveInvoice == null) {
			throw new ReceiveInvoiceNotFoundException("没有找到对应的收票.");
		}

		BeanUtils.copyProperties(request, receiveInvoice);
		handleReceiveInvoiceReference(request, receiveInvoice);
		ReceiveInvoice.onModify(receiveInvoice, byWho);

		return receiveInvoiceRepository.save(receiveInvoice);
	}

	@Override
	public void deleteReceiveInvoiceById(String id, User byWho) {
		ReceiveInvoice receiveInvoice = receiveInvoiceRepository.findById(id);
		if (receiveInvoice == null) {
			throw new ReceiveInvoiceNotFoundException("没有找到对应的收票.");
		}

		if (receiveInvoiceReferenceList != null) {
			receiveInvoiceReferenceList.forEach(ref -> {
				if (ref.hasReference(receiveInvoice)) {
					throw new ReceiveInvoiceException("该收票已被其他业务数据引用,不能删除.");
				}
			});
		}

		receiveInvoiceRepository.delete(receiveInvoice);
	}

	@Override
	public List<ReceiveInvoice> listReceiveInvoicesByProject(String id, User user) {
		Project project = projectRepository.findById(id);
		if (project == null) {
			throw new ProjectNotFoundException("无效的项目id");
		}
		return receiveInvoiceRepository.findListByProjectOrderByCreatedAtDesc(project);
	}

	@Override
	public ReceiveInvoice findReceiveInvoiceById(String id, User byWho) {
		return receiveInvoiceRepository.findById(id);
	}

	@Override
	public Page<ReceiveInvoice> listReceiveInvoices(ReceiveInvoiceQueryRequest request, User byWho) {
		return receiveInvoiceRepository.queryPage(request);
	}

	@Override
	public BigDecimal calculatePaymentAmount(ReceiveInvoice receiveInvoice) {
		return paymentRepository.calculateTotalAmount(receiveInvoice);
	}

	private void handleReceiveInvoiceReference(SaveReceiveInvoiceRequest request, ReceiveInvoice receiveInvoice) {
		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new EntityNotFoundException("无效的项目id");
		}

		SubContract subContract = subContractRepository.findById(request.getSubContractId());
		if (subContract == null) {
			throw new EntityNotFoundException("无效的分包合同id");
		}

		SubContractor subContractor = subContractorRepository.findById(request.getSubContractorId());
		if (subContractor == null) {
			throw new EntityNotFoundException("无效的收票单位id");
		}

		if (!StringUtils.isEmpty(request.getSubOrderId())) {
			SubOrder subOrder = subOrderRepository.findById(request.getSubOrderId());
			if (subOrder == null) {
				throw new EntityNotFoundException("无效的分包订单id");
			}
			receiveInvoice.setSubOrder(subOrder);
		}

		receiveInvoice.setProject(project);
		receiveInvoice.setSubContract(subContract);
		receiveInvoice.setSubContractor(subContractor);
	}

}
