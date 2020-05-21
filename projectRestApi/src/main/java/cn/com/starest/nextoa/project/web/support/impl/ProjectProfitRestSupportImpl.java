package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.MutablePageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlementAggregation;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.PaymentService;
import cn.com.starest.nextoa.project.service.ProjectSettlementService;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.support.ProjectProfitRestSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectProfitRestSupportImpl implements ProjectProfitRestSupport {

    @Autowired
    private ProjectSettlementService projectSettlementService;

    @Autowired
    private FinancialReportService financialReportService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public ProjectSettlementSummary findProjectSettlementById(String id, User byWho) {
        ProjectSettlement projectSettlement = projectSettlementService.findProjectSettlementById(id, byWho);

        ProjectSettlementSummary summary = new ProjectSettlementSummary();
        summary.setId(projectSettlement.getId());

        ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
                projectSettlement, byWho);

        projectSettlementAggregation.setTotalProfit(projectSettlementAggregation.getProfit1()
                                                                                .add(projectSettlementAggregation.getMarketProfit1()));

        BeanUtils.copyProperties(projectSettlementAggregation, summary);
        summary.setAggregation(projectSettlementAggregation);

        return summary;
    }

    @Override
    public Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request, User byWho) {
        Page<ProjectSettlement> projectProfitSettingPage = projectSettlementService.listProjectSettlements(request,
                                                                                                           byWho);

        ProjectSettlementAggregation totalProjectSettlementAggregation = projectSettlementService.calculateProjectSettlementAggregation(
                request,
                byWho);


        List<ProjectSettlementSummary> resultContent = new ArrayList<>();

        findMatchedProjectProfits(request, byWho, projectProfitSettingPage, resultContent);

        if (resultContent.size() >= request.getLimit()) {
            return new PermissionAwarePageImpl<>(resultContent, new PageRequest(request.getStart(), request.getLimit()),
                                                 projectProfitSettingPage.getTotalElements(),
                                                 totalProjectSettlementAggregation,
                                                 projectSettlementService.resolveGrantedActions(byWho));
        }

        for (; projectProfitSettingPage.hasNext(); ) {
            if (request instanceof MutablePageQueryRequest) {
                ((MutablePageQueryRequest) request).setStart(request.getStart() + 1);
            }
            else {
                break;
            }

            projectProfitSettingPage = projectSettlementService.listProjectSettlements(request, byWho);

            findMatchedProjectProfits(request, byWho, projectProfitSettingPage, resultContent);

            if (resultContent.size() >= request.getLimit()) {
                return new PermissionAwarePageImpl<>(resultContent,
                                                     new PageRequest(request.getStart(), request.getLimit()),
                                                     projectProfitSettingPage.getTotalElements(),
                                                     totalProjectSettlementAggregation,
                                                     projectSettlementService.resolveGrantedActions(byWho));
            }
        }

        return new PermissionAwarePageImpl<>(resultContent, new PageRequest(request.getStart(), request.getLimit()),
                                             projectProfitSettingPage.getTotalElements(),
                                             totalProjectSettlementAggregation,
                                             projectSettlementService.resolveGrantedActions(byWho));

    }

    private void findMatchedProjectProfits(ProjectSettlementQueryParameter request, User byWho,
                                           Page<ProjectSettlement> projectProfitSettingPage,
                                           List<ProjectSettlementSummary> resultContent) {
        resultContent.addAll(projectProfitSettingPage.getContent().stream().map(item -> {
            ProjectSettlementSummary summary = new ProjectSettlementSummary();
            summary.setId(item.getId());

            ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
                    item,
                    byWho);

            projectSettlementAggregation.setTotalProfit(projectSettlementAggregation.getProfit1()
                                                                                    .add(projectSettlementAggregation
                                                                                                 .getMarketProfit1()));

            BeanUtils.copyProperties(projectSettlementAggregation, summary);
            summary.setAggregation(projectSettlementAggregation);

            return summary;
        }).filter(item -> {
            if (request.getSettled() != null) {
                if (request.getSettled().booleanValue()) {
                    if (item.getAggregation().getWithdrawAmount1() != null &&
                            item.getAggregation().getWithdrawAmount1().compareTo(
                                    item.getAggregation().getTotalProfit()) == 0) {
                        return true;
                    }

                    return false;
                }
                else {
                    if (item.getAggregation().getWithdrawAmount1() == null ||
                            (item.getAggregation().getTotalProfit().compareTo(BigDecimal.ZERO) > 0 &&
                                    (item.getAggregation()
                                         .getWithdrawAmount1()
                                         .compareTo(item.getAggregation().getTotalProfit()) != 0))) {
                        return true;
                    }

                    return false;
                }
            }

            return true;
        }).collect(Collectors.toList()));
    }

    @Override
    public List<PaymentSummary> getProjectSettlementPaymentList(String id, String userId, User user) {
        return paymentService.listPayments(id, userId).stream().map(PaymentSummary::from).collect(Collectors.toList());
    }
}
