package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.LicenseQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveLicenseRequest;
import cn.com.starest.nextoa.project.domain.rule.LicenseReference;
import cn.com.starest.nextoa.project.exception.ContractException;
import cn.com.starest.nextoa.project.exception.LicenseException;
import cn.com.starest.nextoa.project.exception.LicenseNotFoundException;
import cn.com.starest.nextoa.project.exception.OrderException;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.LicenseRepository;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.LicenseService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author dz
 */
@Service
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired(required = false)
	private List<LicenseReference> licenseReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private LicenseRepository licenseRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.LICENSE, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(License license, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.LICENSE, byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);


		return grantedActions;
	}

	@Override
	public License createLicense(SaveLicenseRequest request, User byWho) {
		License license = new License();

		handleLicenseReference(request, license);
		BeanUtils.copyProperties(request, license);
		License.onCreate(license, byWho);

		return licenseRepository.save(license);
	}

	@Override
	public License updateLicense(String id, SaveLicenseRequest request, User byWho) {
		License license = licenseRepository.findById(id);
		if (license == null) {
			throw new LicenseNotFoundException("没有找到对应的外管证.");
		}

		handleLicenseReference(request, license);
		BeanUtils.copyProperties(request, license);
		License.onModify(license, byWho);

		return licenseRepository.save(license);
	}

	@Override
	public void deleteLicenseById(String id, User byWho) {
		License license = licenseRepository.findById(id);
		if (license == null) {
			throw new LicenseNotFoundException("没有找到对应的外管证.");
		}

		if (licenseReferenceList != null) {
			licenseReferenceList.forEach(ref -> {
				if (ref.hasReference(license)) {
					throw new LicenseException("该外管证已被其他业务数据引用,不能删除.");
				}
			});
		}

		licenseRepository.delete(license);
	}

	@Override
	public License findLicenseById(String id, User byWho) {
		return licenseRepository.findById(id);
	}

	@Override
	public Page<License> listLicenses(LicenseQueryRequest request, User byWho) {
		return licenseRepository.queryPage(request);
	}

	@Override
	public List<License> listLicensesByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		return listLicensesByProject(project, byWho);
	}

	@Override
	public List<License> listLicensesByProject(Project project, User byWho) {
		if (project == null) {
			return Collections.emptyList();
		}
		return licenseRepository.findListByProjectOrderByCreatedAtAsc(project);
	}

	private void handleLicenseReference(SaveLicenseRequest request, License license) {
		if (request.isCancelled() && request.getCancelledAt() == null) {
			throw new LicenseException("请提供核销日期");
		}

		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new LicenseException("无效的项目id");
		}
		license.setProject(project);

		Contract contract = contractRepository.findById(request.getContractId());
		if (contract == null) {
			throw new LicenseException("无效的合同id");
		}

		if (license.getContract() != null &&
			license.getContract().isSettled() &&
			!license.getContract().getId().equals(request.getContractId())) {
			throw new ContractException("外管证对应的合同已经结算,不能修改为其他合同");
		}
		else if (contract.isSettled()) {
			throw new ContractException("不能选择已经结算的合同");
		}

		license.setContract(contract);

		if (license.getOrder() != null &&
			license.getOrder().isSettled() &&
			!license.getOrder().getId().equals(request.getOrderId())) {
			throw new OrderException("外管证对应的订单已经结算,不能修改为其他订单");
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			Order order = orderRepository.findById(request.getOrderId());
			if (order == null) {
				throw new LicenseException("无效的订单id");
			}

			if (license.getOrder() != null &&
				license.getOrder().isSettled() &&
				!license.getOrder().getId().equals(request.getOrderId())) {
				throw new OrderException("外管证对应的订单已经结算,不能修改为其他订单");
			}
			else if (order.isSettled()) {
				throw new OrderException("不能选择已经结算的订单");
			}

			license.setOrder(order);
		}
		else {
			license.setOrder(null);
		}
	}

}
