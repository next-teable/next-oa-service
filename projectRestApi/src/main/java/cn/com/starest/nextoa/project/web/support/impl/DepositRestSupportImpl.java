package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import cn.com.starest.nextoa.project.domain.request.SaveDepositRequest;
import cn.com.starest.nextoa.project.service.DepositService;
import cn.com.starest.nextoa.project.web.dto.DepositDetail;
import cn.com.starest.nextoa.project.web.dto.DepositQueryParameter;
import cn.com.starest.nextoa.project.web.dto.DepositSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.DepositRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class DepositRestSupportImpl implements DepositRestSupport {

	@Autowired
	private DepositService depositService;

	@Override
	public DepositSummary createDeposit(SaveDepositRequest request, User byWho) {
		Deposit deposit = depositService.createDeposit(request, byWho);
		DepositSummary summary = DepositSummary.from(deposit);
		summary.setGrantedActions(depositService.resolveGrantedActions(deposit, byWho));
		return summary;
	}

	@Override
	public DepositSummary updateDeposit(String id, SaveDepositRequest request, User byWho) {
		Deposit deposit = depositService.updateDeposit(id, request, byWho);
		DepositSummary summary = DepositSummary.from(deposit);
		summary.setGrantedActions(depositService.resolveGrantedActions(deposit, byWho));
		return summary;
	}

	@Override
	public DepositDetail findDepositById(String id, User byWho) {
		Deposit deposit = depositService.findDepositById(id, byWho);
		DepositDetail summary = DepositDetail.from(deposit);
		summary.setGrantedActions(depositService.resolveGrantedActions(deposit, byWho));
		return summary;
	}

	@Override
	public Page<DepositSummary> listDeposits(DepositQueryParameter request, User byWho) {
		Page<Deposit> depositPage = depositService.listDeposits(request, byWho);
		return new PermissionAwarePageImpl<>(depositPage.getContent().stream().map(item -> {
			DepositSummary summary = DepositSummary.from(item);
			summary.setGrantedActions(depositService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 depositPage.getTotalElements(),
											 depositService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteDepositById(String id, User user) {
		depositService.deleteDepositById(id, user);
	}

}
