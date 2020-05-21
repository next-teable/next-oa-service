package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.SystemSetting;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlementAggregation;
import cn.com.starest.nextoa.project.exception.ProjectSettlementException;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.PaymentService;
import cn.com.starest.nextoa.project.service.ProjectCompletionService;
import cn.com.starest.nextoa.project.service.ProjectSettlementService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectSettlementRestSupport;
import cn.com.starest.nextoa.service.SystemSettingService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectSettlementRestSupportImpl implements ProjectSettlementRestSupport {

	@Autowired
	private ProjectCompletionService projectCompletionService;

	@Autowired
	private ProjectSettlementService projectSettlementService;

	@Autowired
	private FinancialReportService financialReportService;

	@Autowired
	private SystemSettingService systemSettingService;

	@Autowired
	private PaymentService paymentService;

	@Override
	public Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request, User byWho) {
		Page<ProjectCompletion> projectCompletionPage = projectCompletionService.listProjectCompletions(request, byWho);
		return new PermissionAwarePageImpl<>(projectCompletionPage.getContent().stream().map(item -> {
			ProjectCompletionSummary summary = ProjectCompletionSummary.from(item);
			summary.setGrantedActions(projectSettlementService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 projectCompletionPage.getTotalElements(),
											 projectSettlementService.resolveGrantedActions(byWho));
	}

	@Override
	public ProjectCompletionDetail findProjectCompletionById(String id, User byWho) {
		ProjectCompletion projectCompletion = projectCompletionService.findProjectCompletionById(id, byWho);
		return ProjectCompletionDetail.from(projectCompletion);
	}

	@Override
	public ProjectSettlementSummary prepareProjectSettlement(String projectCompletionId, User byWho) {
		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		List<User> userList = systemSetting.getProfitSupervisors();
		if (userList == null || userList.size() != 3) {
			throw new ProjectSettlementException("请联系系统管理员设置可提成的三位领导");
		}

		ProjectCompletion projectCompletion = projectCompletionService.findProjectCompletionById(projectCompletionId,
																								 byWho);
		if (projectCompletion == null) {
			return null;
		}

		ProjectSettlement projectSettlement = new ProjectSettlement();
		projectSettlement.setUser1(userList.get(0));
		projectSettlement.setUser2(userList.get(1));
		projectSettlement.setUser3(userList.get(2));
		BeanUtils.copyProperties(projectCompletion, projectSettlement);
		return ProjectSettlementSummary.from(projectSettlement);
	}

	@Override
	public ProjectSettlementSummary createProjectSettlement(String id,
															SaveProjectSettlementParameter request,
															User byWho) {
		ProjectSettlement projectProfitSetting = projectSettlementService.createProjectSettlement(id, request, byWho);
		ProjectSettlementSummary summary = ProjectSettlementSummary.from(projectProfitSetting);
		return summary;
	}

	@Override
	public ProjectSettlementSummary updateProjectSettlement(String id,
															SaveProjectSettlementParameter request,
															User byWho) {
		ProjectSettlement projectProfitSetting = projectSettlementService.updateProjectSettlement(id, request, byWho);
		ProjectSettlementSummary summary = ProjectSettlementSummary.from(projectProfitSetting);
		return summary;
	}

	@Override
	public void doProjectSettlement(String id, User user) {
		projectSettlementService.doProjectSettlement(id, user);
	}

	@Override
	public void deleteProjectSettlementById(String id, User user) {
		projectSettlementService.deleteProjectSettlementById(id, user);
	}

	@Override
	public ProjectSettlementSummary findProjectSettlementById(String id, User byWho) {
		ProjectSettlement projectSettlement = projectSettlementService.findProjectSettlementById(id, byWho);
		ProjectSettlementSummary summary = new ProjectSettlementSummary();
		ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregation(
				projectSettlement);
		BeanUtils.copyProperties(projectSettlementAggregation, summary);
		summary.setAggregation(projectSettlementAggregation);
		return summary;
	}

	@Override
	public Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request, User byWho) {
		Page<ProjectSettlement> projectProfitSettingPage = projectSettlementService.listProjectSettlements(request);
		return new PermissionAwarePageImpl<>(projectProfitSettingPage.getContent().stream().map(item -> {
			ProjectSettlementSummary summary = new ProjectSettlementSummary();
			summary.setId(item.getId());

			ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregation(
					item);
			summary.setAggregation(projectSettlementAggregation);

			List<ModuleActions.ModuleAction> moduleActions = Lists.newArrayList(projectSettlementService.resolveGrantedActions(
					item,
					byWho));

			if ((projectSettlementAggregation.getWithdrawAmount1() != null &&
				 projectSettlementAggregation.getWithdrawAmount1().compareTo(BigDecimal.ZERO) > 0) ||
				(projectSettlementAggregation.getWithdrawAmount2() != null &&
				 projectSettlementAggregation.getWithdrawAmount2().compareTo(BigDecimal.ZERO) > 0) ||
				(projectSettlementAggregation.getWithdrawAmount3() != null &&
				 projectSettlementAggregation.getWithdrawAmount3().compareTo(BigDecimal.ZERO) > 0) ||
				(projectSettlementAggregation.getWithdrawAmount4() != null &&
				 projectSettlementAggregation.getWithdrawAmount4().compareTo(BigDecimal.ZERO) > 0) ||
				(projectSettlementAggregation.getWithdrawAmount5() != null &&
				 projectSettlementAggregation.getWithdrawAmount5().compareTo(BigDecimal.ZERO) > 0)) {
				moduleActions.removeIf(action -> action == ModuleActions.DELETE_ACTION);
			}

			summary.setGrantedActions(moduleActions.toArray(ModuleActions.EMPTY_ACTIONS));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 projectProfitSettingPage.getTotalElements(),
											 projectSettlementService.resolveGrantedActions(byWho));
	}

	@Override
	public List<PaymentSummary> getProjectSettlementPaymentList(String id, String userId, User user) {
		return paymentService.listPayments(id, userId).stream().map(PaymentSummary::from).collect(Collectors.toList());
	}
}
