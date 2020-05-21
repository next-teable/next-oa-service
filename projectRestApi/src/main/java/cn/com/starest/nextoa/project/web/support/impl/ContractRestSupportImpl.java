package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.ContractHistory;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveContractRequest;
import cn.com.starest.nextoa.project.service.ContractService;
import cn.com.starest.nextoa.project.web.dto.ContractDetail;
import cn.com.starest.nextoa.project.web.dto.ContractHistorySummary;
import cn.com.starest.nextoa.project.web.dto.ContractSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.ContractRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ContractRestSupportImpl implements ContractRestSupport {

	@Autowired
	private ContractService contractService;

	@Override
	public ContractSummary createContract(SaveContractRequest request, User byWho) {
		Contract contract = contractService.createContract(request, byWho);
		ContractSummary result = ContractSummary.from(contract);
		result.setGrantedActions(contractService.resolveGrantedActions(contract, byWho));
		return result;
	}

	@Override
	public ContractSummary updateContract(String id, SaveContractRequest request, User byWho) {
		Contract contract = contractService.updateContract(id, request, byWho);
		ContractSummary result = ContractSummary.from(contract);
		result.setGrantedActions(contractService.resolveGrantedActions(contract, byWho));
		return result;
	}

	@Override
	public void publishContract(String id, User byWho) {
		contractService.publishContract(id, byWho);
	}

	@Override
	public void unpublishContract(String id, User byWho) {
		contractService.unpublishContract(id, byWho);
	}

	@Override
	public ContractDetail findContractById(String id, User byWho) {
		Contract contract = contractService.findContractById(id, byWho);
		ContractDetail result = ContractDetail.from(contract);
		result.setGrantedActions(contractService.resolveGrantedActions(contract, byWho));
		return result;
	}

	@Override
	public Page<ContractSummary> listContracts(ContractQueryRequest request, User byWho) {
		Page<Contract> contractPage = contractService.listContracts(request, byWho);
		return new PermissionAwarePageImpl<>(contractPage.getContent().stream().map(item -> {
			ContractSummary contractSummary = ContractSummary.from(item);
			contractSummary.setGrantedActions(contractService.resolveGrantedActions(item, byWho));
			return contractSummary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 contractPage.getTotalElements(),
											 contractService.resolveGrantedContractActions(byWho));
	}

	@Override
	public void deleteContractById(String id, User user) {
		contractService.deleteContractById(id, user);
	}

	@Override
	public Page<ContractHistorySummary> listContractHistories(String id, PageQueryParameter request, User user) {
		Page<ContractHistory> contractHistoryPage = contractService.listContractHistories(id, request);
		return new PageImpl<>(contractHistoryPage.getContent()
												 .stream()
												 .map(ContractHistorySummary::from)
												 .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  contractHistoryPage.getTotalElements());
	}

	@Override
	public ContractHistorySummary findContractHistoryById(String id, User user) {
		return ContractHistorySummary.from(contractService.findContractHistoryById(id));
	}
}
