package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.model.SubContractHistory;
import cn.com.starest.nextoa.project.domain.request.SaveSubContractRequest;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import cn.com.starest.nextoa.project.service.SubContractService;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.SubContractDetail;
import cn.com.starest.nextoa.project.web.dto.SubContractHistorySummary;
import cn.com.starest.nextoa.project.web.dto.SubContractSummary;
import cn.com.starest.nextoa.project.web.support.SubContractRestSupport;
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
public class SubContractRestSupportImpl implements SubContractRestSupport {

	@Autowired
	private SubContractService subContractService;

	@Override
	public SubContractSummary createSubContract(SaveSubContractRequest request, User byWho) {
		SubContract subContract = subContractService.createSubContract(request, byWho);
		SubContractSummary result = SubContractSummary.from(subContract);
		result.setGrantedActions(subContractService.resolveGrantedActions(subContract, byWho));
		return result;
	}

	@Override
	public SubContractSummary updateSubContract(String id, SaveSubContractRequest request, User byWho) {
		SubContract subContract = subContractService.updateSubContract(id, request, byWho);
		SubContractSummary result = SubContractSummary.from(subContract);
		result.setGrantedActions(subContractService.resolveGrantedActions(subContract, byWho));
		return result;
	}

	@Override
	public void publishSubContract(String id, User byWho) {
		subContractService.publishSubContract(id, byWho);
	}

	@Override
	public void unpublishSubContract(String id, User byWho) {
		subContractService.unpublishSubContract(id, byWho);
	}


	@Override
	public SubContractDetail findSubContractById(String id, User byWho) {
		SubContract subContract = subContractService.findSubContractById(id, byWho);
		SubContractDetail result = SubContractDetail.from(subContract);
		result.setGrantedActions(subContractService.resolveGrantedActions(subContract, byWho));
		return result;
	}

	@Override
	public Page<SubContractSummary> listSubContracts(SubContractQueryRequest request, User byWho) {
		Page<SubContract> subContractPage = subContractService.listSubContracts(request, byWho);
		return new PermissionAwarePageImpl<>(subContractPage.getContent().stream().map(item -> {
			SubContractSummary subContractSummary = SubContractSummary.from(item);
			subContractSummary.setGrantedActions(subContractService.resolveGrantedActions(item, byWho));
			return subContractSummary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 subContractPage.getTotalElements(),
											 subContractService.resolveGrantedSubContractActions(byWho));
	}

	@Override
	public void deleteSubContractById(String id, User user) {
		subContractService.deleteSubContractById(id, user);
	}

	@Override
	public Page<SubContractHistorySummary> listSubContractHistories(String id, PageQueryParameter request, User user) {
		Page<SubContractHistory> subContractHistoryPage = subContractService.listSubContractHistories(id, request);
		return new PageImpl<>(subContractHistoryPage.getContent()
													.stream()
													.map(SubContractHistorySummary::from)
													.collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  subContractHistoryPage.getTotalElements());
	}

	@Override
	public SubContractHistorySummary findSubContractHistoryById(String id, User user) {
		return SubContractHistorySummary.from(subContractService.findSubContractHistoryById(id));
	}
}
