package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.service.*;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectMonitorRestSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectMonitorRestSupportImpl implements ProjectMonitorRestSupport {

    private static final Log logger = LogFactory.getLog(ProjectMonitorRestSupportImpl.class);
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectSortService projectSortService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SubOrderService subOrderService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private SubContractService subContractService;
    @Autowired
    private LicenseService certificateService;
    @Autowired
    private IssueInvoiceService issueInvoiceService;
    @Autowired
    private ReceiveInvoiceService receiveInvoiceService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReimburseService reimburseService;
    @Autowired
    private PendingPaymentService pendingPaymentService;
    @Autowired
    private ProjectReceivedPaymentService projectReceivedPaymentService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private AggregationService aggregationService;
    @Autowired
    private ProjectCompletionService projectCompletionService;
    @Autowired
    private ProjectSettlementService projectSettlementService;
    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public Page<ProjectFinancialAggregation> getProjectFinancialReport(ProjectQueryParameter queryParameter,
                                                                       User user) {
        queryParameter.setStatus(ProjectStatus.DRAFT);
        queryParameter.setStatusScope(ProjectQueryRequest.ProjectStatusScope.EXCLUDE);
        Page<Project> projectPage = projectService.listSupervisedProjects(queryParameter, user);

        List<ProjectFinancialAggregation> projectFinancialReportList = projectPage.getContent()
                .stream()
                .map(project -> financialReportService
                        .calculateProjectFinancialReport(
                                project,
                                user))
                .collect(Collectors.toList());

        if (!StringUtils.isEmpty(queryParameter.getFrameworkContractId())) {
            ProjectFinancialAggregation projectFinancialAggregation = sumProjectFinancialReport(queryParameter, user);
            return new ExtendedPageImpl<>(projectFinancialReportList,
                    new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                    projectPage.getTotalElements(),
                    projectFinancialAggregation);
        }

        return new PageImpl<>(projectFinancialReportList,
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                projectPage.getTotalElements());
    }

    @Override
    public Page<ProjectFinancialAggregation> getProjectFinancialReportByYear(ProjectQueryParameter queryParameter,
                                                                             User user) {
        queryParameter.setStatus(ProjectStatus.DRAFT);
        queryParameter.setStatusScope(ProjectQueryRequest.ProjectStatusScope.EXCLUDE);
//        Page<Project> projectPage = projectService.listSupervisedProjects(queryParameter, user);
        Page<ProjectManageRelationship> relationshipPage = projectService.listSupervisedProjectManageRelationship(queryParameter, user);

        List<ProjectFinancialAggregation> projectFinancialReportList = relationshipPage.getContent()
                .stream()
                .map(relationship -> financialReportService
                        .calculateProjectFinancialReport(
                                relationship.getProject(),
                                user, relationship.getYear()))
                .collect(Collectors.toList());

        if (!StringUtils.isEmpty(queryParameter.getFrameworkContractId())) {
            ProjectFinancialAggregation projectFinancialAggregation = sumProjectFinancialReport(queryParameter, user);
            return new ExtendedPageImpl<>(projectFinancialReportList,
                    new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                    relationshipPage.getTotalElements(),
                    projectFinancialAggregation);
        }

        return new PageImpl<>(projectFinancialReportList,
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                relationshipPage.getTotalElements());
    }

    @Override
    public List<OrderSummary> listProjectOrders(String id, int year, User user) {
        return orderService.listOrdersByProject(id, year, user).stream().map(OrderSummary::from).collect(Collectors.toList());
    }

    public ProjectFinancialAggregation sumProjectFinancialReport(ProjectQueryParameter queryParameter, User user) {
        ProjectQueryParameter newQueryParameter = new ProjectQueryParameter();
        BeanUtils.copyProperties(queryParameter, newQueryParameter);
        newQueryParameter.setStatus(ProjectStatus.DRAFT);
        newQueryParameter.setStatusScope(ProjectQueryRequest.ProjectStatusScope.EXCLUDE);
        newQueryParameter.setStart(0);
        newQueryParameter.setLimit(1000);

        return financialReportService.sumProjectFinancialReport(newQueryParameter, user);
    }

    @Override
    public ProjectDetail findProjectById(String id, User user) {
        return ProjectDetail.from(projectService.findProjectById(id, user));
    }

    @Override
    public List<ContractSummary> listProjectContracts(String id, User user) {
        return contractService.listContractsByProject(id, user)
                .stream()
                .map(ContractSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderSummary> listProjectOrders(String id, User user) {
        return orderService.listOrdersByProject(id, user).stream().map(OrderSummary::from).collect(Collectors.toList());
    }

    @Override
    public List<SubContractSummary> listProjectSubContracts(String id, User user) {
        return subContractService.listSubContractsByProject(id, user)
                .stream()
                .map(SubContractSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubOrderSummary> listProjectSubOrders(String id, User user) {
        return subOrderService.listSubOrdersByProject(id, user)
                .stream()
                .map(SubOrderSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueInvoiceSummary> listProjectIssueInvoices(String id, User user) {
        return issueInvoiceService.listIssueInvoicesByProject(id, user)
                .stream()
                .map(item -> {
                    IssueInvoiceSummary summary = IssueInvoiceSummary.from(item);
                    summary.setReceivedAmount(issueInvoiceService.calculateReceivedAmount(item));
                    return summary;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiveInvoiceSummary> listProjectReceiveInvoices(String id, User user) {
        return receiveInvoiceService.listReceiveInvoicesByProject(id, user)
                .stream()
                .map(item -> {
                    ReceiveInvoiceSummary summary = ReceiveInvoiceSummary.from(item);
                    summary.setPaymentAmount(receiveInvoiceService.calculatePaymentAmount(item));
                    return summary;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<LicenseSummary> listProjectLicenses(String id, User user) {
        return certificateService.listLicensesByProject(id, user)
                .stream()
                .map(LicenseSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepositSummary> listProjectDeposits(String id, User user) {
        return depositService.listDepositsByProject(id, user)
                .stream()
                .map(DepositSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public Page<PaymentSummary> listProjectPayments(String id, PageQueryParameter queryParameter, User user) {
        PaymentQueryParameter paymentQueryParameter = new PaymentQueryParameter();
        paymentQueryParameter.setProjectId(id);
        paymentQueryParameter.setStart(queryParameter.getStart());
        paymentQueryParameter.setLimit(queryParameter.getLimit());
        Page<Payment> paymentPage = paymentService.listPayments(paymentQueryParameter, user);

        return new PageImpl<>(paymentPage.getContent().stream().map(PaymentSummary::from).collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                paymentPage.getTotalElements());
    }

    @Override
    public Page<ReimburseSummary> listProjectReimburses(String id, PageQueryParameter queryParameter, User user) {
        ReimburseQueryParameter reimburseQueryParameter = new ReimburseQueryParameter();
        reimburseQueryParameter.setProjectId(id);
        reimburseQueryParameter.setStart(queryParameter.getStart());
        reimburseQueryParameter.setLimit(queryParameter.getLimit());

        Page<Reimburse> reimbursePage = reimburseService.listReimburses(reimburseQueryParameter, user);

        return new PageImpl<>(reimbursePage.getContent()
                .stream()
                .map(ReimburseSummary::from)
                .collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                reimbursePage.getTotalElements());
    }

    @Override
    public Page<PendingPaymentSummary> listProjectPendingPayments(String id,
                                                                  PageQueryParameter queryParameter,
                                                                  User user) {
        PendingPaymentQueryParameter pendingPaymentQueryParameter = new PendingPaymentQueryParameter();
        pendingPaymentQueryParameter.setProjectId(id);
        pendingPaymentQueryParameter.setStart(queryParameter.getStart());
        pendingPaymentQueryParameter.setLimit(queryParameter.getLimit());

        Page<PendingPayment> pendingPaymentPage = pendingPaymentService.listPendingPayments(pendingPaymentQueryParameter,
                user);

        return new PageImpl<>(pendingPaymentPage.getContent()
                .stream()
                .map(PendingPaymentSummary::from)
                .collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                pendingPaymentPage.getTotalElements());
    }

    @Override
    public Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(String id,
                                                                           PageQueryParameter queryParameter,
                                                                           User user) {
        ProjectReceivedPaymentQueryParameter projectReceivedPaymentQueryParameter = new ProjectReceivedPaymentQueryParameter();
        projectReceivedPaymentQueryParameter.setProjectId(id);
        projectReceivedPaymentQueryParameter.setStart(queryParameter.getStart());
        projectReceivedPaymentQueryParameter.setLimit(queryParameter.getLimit());

        Page<ProjectReceivedPayment> projectReceivedPaymentPage = projectReceivedPaymentService.listProjectReceivedPayments(
                projectReceivedPaymentQueryParameter,
                user);

        return new PageImpl<>(projectReceivedPaymentPage.getContent()
                .stream()
                .map(ProjectReceivedPaymentSummary::from)
                .collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                projectReceivedPaymentPage.getTotalElements());
    }

    @Override
    public Page<SalarySummary> listProjectSalaries(String id, PageQueryParameter queryParameter, User user) {
        SalaryQueryParameter salaryQueryParameter = new SalaryQueryParameter();
        salaryQueryParameter.setProjectId(id);
        salaryQueryParameter.setStart(queryParameter.getStart());
        salaryQueryParameter.setLimit(queryParameter.getLimit());

        Page<Salary> salaryPage = salaryService.listSalaries(salaryQueryParameter, user);

        return new PageImpl<>(salaryPage.getContent().stream().map(SalarySummary::from).collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                salaryPage.getTotalElements());
    }

    @Override
    public Page<SalarySummary> listProjectSalaries(String id,
                                                   int year,
                                                   int month,
                                                   SalaryQueryParameter queryParameter,
                                                   User user) {
        SalaryQueryParameter salaryQueryParameter = new SalaryQueryParameter();
        salaryQueryParameter.setProjectId(id);
        salaryQueryParameter.setYear(year);
        salaryQueryParameter.setMonth(month);
        salaryQueryParameter.setStart(queryParameter.getStart());
        salaryQueryParameter.setLimit(queryParameter.getLimit());
        salaryQueryParameter.setEmployee(queryParameter.getEmployee());

        Page<Salary> salaryPage = salaryService.listSalaries(salaryQueryParameter, user);

        return new PageImpl<>(salaryPage.getContent().stream().map(SalarySummary::from).collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                salaryPage.getTotalElements());
    }

    @Override
    public List<SalaryAggregationByMonth> listSalaryAggregationByMonth(String id,
                                                                       PageQueryParameter queryParameter,
                                                                       User user) {
        int beginYear = 2016;
        int endYear = Calendar.getInstance().get(Calendar.YEAR);

        if (endYear <= beginYear) {
            return Collections.emptyList();
        }

        Project project = projectService.findProjectById(id, user);
        if (project == null) {
            return Collections.emptyList();
        }

        List<SalaryAggregationByMonth> result = new ArrayList<>();

        for (int year = beginYear; year <= endYear; year++) {
            for (int month = 1; month <= 12; month++) {
                BigDecimal totalPay = salaryService.calculateProjectSalary(id, year, month);
                if (totalPay.compareTo(BigDecimal.ZERO) > 0) {
                    SalaryAggregationByMonth item = new SalaryAggregationByMonth();
                    item.setSubjectId(id);
                    item.setSubjectName(project.getName());
                    item.setYear(year);
                    item.setMonth(month);
                    item.setTotalPay(totalPay);
                    result.add(item);
                }
            }
        }

        return result;
    }

    @Override
    public ProjectAggregation listProjectAggregation(String id, User user) {
        Project project = projectService.findProjectById(id, user);
        if (project == null) {
            return null;
        }
        ProjectAggregation result = new ProjectAggregation();
        result.setId(id);
        result.setContractTotalAmount(aggregationService.getContractTotalAmount(project));
        result.setIssueInvoiceTotalAmount(aggregationService.getIssueInvoiceTotalAmount(project));
        result.setOrderTotalAmount(aggregationService.getOrderTotalAmount(project));
        result.setPaymentTotalAmount(aggregationService.getPaymentTotalAmount(project));
        result.setProjectReceivedPaymentTotalAmount(aggregationService.getProjectReceivedPaymentTotalAmount(project));
        result.setReceiveInvoiceTotalAmount(aggregationService.getReceiveInvoiceTotalAmount(project));
        result.setSubOrderTotalAmount(aggregationService.getSubOrderTotalAmount(project));
        result.setDepositTotalAmount(aggregationService.getDepositTotalAmount(project));
        result.setPendingPaymentTotalAmount(aggregationService.getPendingPaymentTotalAmount(project));
        result.setSalaryTotalAmount(aggregationService.getSalaryTotalAmount(project));
        return result;
    }

    @Override
    public void resortProjects(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        projectSortService.sortProjects();
        logger.debug(String.format("resort project takes %d mills", System.currentTimeMillis() - currentTimeMillis));
    }

    @Override
    public void clearProjectSorts(User user) {
        projectSortService.clearProjectSorts();
    }

    @Override
    public Page<ProjectCompletionSummary> listProjectCompletions(String id, User byWho) {
        ProjectCompletionQueryParameter queryParameter = new ProjectCompletionQueryParameter();
        queryParameter.setProjectId(id);
        Page<ProjectCompletion> projectCompletionPage = projectCompletionService.listProjectCompletions(queryParameter,
                byWho);

        return new PageImpl<>(projectCompletionPage.getContent()
                .stream()
                .map(ProjectCompletionSummary::from)
                .collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                projectCompletionPage.getTotalElements());
    }

    @Override
    public Page<ProjectSettlementSummary> listProjectSettlements(String id, User byWho) {
        ProjectSettlementQueryParameter queryParameter = new ProjectSettlementQueryParameter();
        queryParameter.setProjectId(id);

        Page<ProjectSettlement> projectProfitSettingPage = projectSettlementService.listProjectSettlements(
                queryParameter);
        return new PageImpl<>(projectProfitSettingPage.getContent().stream().map(item -> {
            ProjectSettlementSummary summary = new ProjectSettlementSummary();
            summary.setId(item.getId());

            ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregation(
                    item);
            summary.setAggregation(projectSettlementAggregation);

            return summary;
        }).collect(Collectors.toList()),
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                projectProfitSettingPage.getTotalElements());
    }

}
