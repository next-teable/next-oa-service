package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.model.SubContractor;
import cn.com.starest.nextoa.project.domain.request.DefaultReimburseContext;
import cn.com.starest.nextoa.project.domain.request.ReimburseContext;
import cn.com.starest.nextoa.project.domain.request.SaveReimburseRequest;
import cn.com.starest.nextoa.project.service.LendingService;
import cn.com.starest.nextoa.project.service.ReimburseService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ReimburseRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ReimburseRestSupportImpl implements ReimburseRestSupport {

	@Autowired
	private ReimburseService reimburseService;

	@Autowired
	private LendingService lendingService;

	@Override
	public ReimburseSummary createReimburse(SaveReimburseRequest request, User byWho) {
		Reimburse reimburse = reimburseService.createReimburse(request, byWho);
		ReimburseSummary result = ReimburseSummary.from(reimburse);
		return result;
	}

	@Override
	public ReimburseSummary updateReimburse(String id, SaveReimburseRequest request, User byWho) {
		Reimburse reimburse = reimburseService.updateReimburse(id, request, byWho);
		ReimburseSummary result = ReimburseSummary.from(reimburse);
		return result;
	}

	@Override
	public ReimburseDetail findReimburseById(String id, User byWho) {
		Reimburse reimburse = reimburseService.findReimburseById(id, byWho);
		ReimburseDetail result = ReimburseDetail.from(reimburse);
		if (reimburse.getPendingPayment() != null) {
			result.setPendingPayment(PendingPaymentSummary.from(reimburse.getPendingPayment()));
		}
		return result;
	}

	@Override
	public Page<ReimburseSummary> listReimburses(final ReimburseQueryParameter request, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = ModuleActions.EMPTY_ACTIONS;
		if (request.getRequestReferType() != null && !StringUtils.isEmpty(request.getRequestReferId())) {
			grantedActions = reimburseService.resolveGrantedActions(new DefaultReimburseContext(request.getRequestReferType(),
																								request.getRequestReferId()),
																	byWho);
		}

		Page<Reimburse> reimbursePage = reimburseService.listReimburses(request, byWho);
		return new PermissionAwarePageImpl<>(reimbursePage.getContent().stream().map(item -> {
			ReimburseSummary reimburseSummary = ReimburseSummary.from(item);
			if (request.getRequestReferType() != null && !StringUtils.isEmpty(request.getRequestReferId())) {
				reimburseSummary.setGrantedActions(reimburseService.resolveGrantedActions(new DefaultReimburseContext(
						request.getRequestReferType(),
						request.getRequestReferId()), item, byWho));
			}
			return reimburseSummary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 reimbursePage.getTotalElements(),
											 grantedActions);
	}

	@Override
	public void deleteReimburse(String id, ReimburseContext context, User byWho) {
		reimburseService.deleteReimburseById(id, context, byWho);
	}

	@Override
	public ReimburseSummary settleReimburse(String id, ReimburseContext context, User byWho) {
		Reimburse reimburse = reimburseService.settleReimburse(id, context, byWho);
		ReimburseSummary result = ReimburseSummary.from(reimburse);
		return result;
	}

	@Override
	public String beforeSettleReimburse(String id, User byWho) {
		Reimburse reimburse = reimburseService.findReimburseById(id, byWho);
		if (reimburse == null) {
			return null;
		}

		return beforeSettleReimburse(reimburse, byWho);
	}

	//计算付款单位,付款人员已借款的情况
	private String beforeSettleReimburse(Reimburse reimburse, User byWho) {
		// 付款人员
		User user = reimburse.getPayTo();

		if (user != null) {
			LendingAggregation lendingAggregation = lendingService.findLendingAggregationById(user.getId(), byWho);
			if (lendingAggregation == null) {
				return null;
			}

			BigDecimal owedAmount = lendingAggregation.getOwedAmount();
			if (owedAmount.compareTo(BigDecimal.ZERO) <= 0) {
				return String.format("%s目前有欠款%.2f", user.getUsername(), owedAmount);
			}
		}

		// 付款单位
		SubContractor subContractor = reimburse.getSubContractor();

		if (subContractor != null) {
			LendingAggregation lendingAggregation = lendingService.findLendingAggregationById(subContractor.getId(),
																							  byWho);
			if (lendingAggregation == null) {
				return null;
			}
			BigDecimal owedAmount = lendingAggregation.getOwedAmount();
			if (owedAmount.compareTo(BigDecimal.ZERO) <= 0) {
				return String.format("%s目前有欠款%.2f", subContractor.getName(), owedAmount);
			}
		}

		return null;
	}

	@Override
	public List<String> beforeSettleReimburseByPaper(String paperId, User user) {
		List<Reimburse> reimburses = reimburseService.listReimbursesByPaperId(paperId);
		if (reimburses == null || reimburses.isEmpty()) {
			return null;
		}

		List<String> result = new ArrayList<>();

		for (Reimburse reimburse : reimburses) {
			String message = beforeSettleReimburse(reimburse, user);
			if (!StringUtils.isEmpty(message)) {
				result.add(message);
			}
		}

		if (result.isEmpty()) {
			return null;
		}

		return result;
	}

	@Override
	public void settleReimbursesByPaper(String paperId, ReimburseContext context, User byWho) {
		reimburseService.settleBatchReimburses(paperId, context, byWho);
	}

	@Override
	public void reimbursePendingPayment(String id, SaveReimbursePendingPaymentParameter request, User user) {
		reimburseService.reimbursePendingPayment(id, request, user);
	}
}
