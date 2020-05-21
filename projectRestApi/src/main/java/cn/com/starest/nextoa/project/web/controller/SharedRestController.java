package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.openapi.support.SystemSettingRestSupport;
import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("公共查询服务")
@RestController
@RequestMapping("/api/shared")
public class SharedRestController {

    @Autowired
    private ProjectRestSupport projectRestSupport;

    @Autowired
    private ContractRestSupport contractRestSupport;

    @Autowired
    private SubContractRestSupport subContractRestSupport;

    @Autowired
    private OrderRestSupport orderRestSupport;

    @Autowired
    private SubOrderRestSupport subOrderRestSupport;

    @Autowired
    private ReimburseRestSupport reimburseRestSupport;

    @Autowired
    private IssueInvoiceRestSupport issueInvoiceRestSupport;

    @Autowired
    private ReceiveInvoiceRestSupport receiveInvoiceRestSupport;

    @Autowired
    private ProjectReceivedPaymentRestSupport projectReceivedPaymentRestSupport;

    @Autowired
    private CompanyReceivedPaymentRestSupport companyReceivedPaymentRestSupport;

    @Autowired
    private LicenseRestSupport licenseRestSupport;

    @Autowired
    private DepositRestSupport depositRestSupport;

    @Autowired
    private PendingPaymentRestSupport pendingPaymentRestSupport;

    @Autowired
    private PaymentRestSupport paymentRestSupport;

    @Autowired
    private ProjectCompletionRestSupport projectCompletionRestSupport;

    @Autowired
    private ProjectSettlementRestSupport projectSettlementRestSupport;

    @Autowired
    private SystemSettingRestSupport systemSettingRestSupport;

    @Autowired
    private AccountSubjectRestSupport accountSubjectRestSupport;

    @ApiOperation(value = "查看项目列表")
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Page<ProjectSummary> listProjects(ProjectQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        request.setStatus(ProjectStatus.RUNNING);
        if (!StringUtils.isEmpty(request.getSubAccountSubjectId())) {
            AccountSubjectSummary accountSubject = accountSubjectRestSupport.findAccountSubjectById(request.getSubAccountSubjectId());
            if (accountSubject != null && accountSubject.getName()
                                                        .equals(systemSettingRestSupport.getSystemSetting()
                                                                                        .getProfitAccountSubjectName())) {
                request.setStatus(ProjectStatus.DRAFT);
                request.setStatusScope(ProjectQueryRequest.ProjectStatusScope.EXCLUDE);
            }
        }
        return projectRestSupport.listProjects(request, user);
    }

    @ApiOperation(value = "查看项目")
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ProjectDetail findProjectById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectRestSupport.findProjectById(id, user);
    }

    @ApiOperation(value = "查看主合同列表")
    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public Page<ContractSummary> listContracts(ContractQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        request.setPublished(true);
        return contractRestSupport.listContracts(request, user);
    }

    @ApiOperation(value = "查看主合同")
    @RequestMapping(value = "/contracts/{id}", method = RequestMethod.GET)
    public ContractDetail findContractById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return contractRestSupport.findContractById(id, user);
    }

    @ApiOperation(value = "查看分包合同列表")
    @RequestMapping(value = "/subContracts", method = RequestMethod.GET)
    public Page<SubContractSummary> listSubContracts(SubContractQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        request.setPublished(true);
        return subContractRestSupport.listSubContracts(request, user);
    }

    @ApiOperation(value = "查看分包合同")
    @RequestMapping(value = "/subContracts/{id}", method = RequestMethod.GET)
    public SubContractDetail findSubContractById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return subContractRestSupport.findSubContractById(id, user);
    }

    @ApiOperation(value = "查看主订单列表")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Page<OrderSummary> listOrders(OrderQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        request.setPublished(true);
        return orderRestSupport.listOrders(request, user);
    }

    @ApiOperation(value = "查看主订单")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public OrderDetail findOrderById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return orderRestSupport.findOrderById(id, user);
    }

    @ApiOperation(value = "查看分包订单列表")
    @RequestMapping(value = "/subOrders", method = RequestMethod.GET)
    public Page<SubOrderSummary> listSubOrders(SubOrderQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        request.setPublished(true);
        return subOrderRestSupport.listSubOrders(request, user);
    }

    @ApiOperation(value = "查看分包订单")
    @RequestMapping(value = "/subOrders/{id}", method = RequestMethod.GET)
    public SubOrderDetail findSubOrderById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return subOrderRestSupport.findSubOrderById(id, user);
    }

    @ApiOperation(value = "查看报销申请列表")
    @RequestMapping(value = "/reimburses", method = RequestMethod.GET)
    public Page<ReimburseSummary> listReimburses(ReimburseQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return reimburseRestSupport.listReimburses(request, user);
    }

    @ApiOperation(value = "查看报销申请")
    @RequestMapping(value = "/reimburses/{id}", method = RequestMethod.GET)
    public ReimburseDetail findReimburseById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return reimburseRestSupport.findReimburseById(id, user);
    }

    @ApiOperation(value = "查看开票列表")
    @RequestMapping(value = "/issueInvoices", method = RequestMethod.GET)
    public Page<IssueInvoiceSummary> listIssueInvoices(IssueInvoiceQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return issueInvoiceRestSupport.listIssueInvoices(request, user);
    }

    @ApiOperation(value = "查看开票")
    @RequestMapping(value = "/issueInvoices/{id}", method = RequestMethod.GET)
    public IssueInvoiceDetail findIssueInvoiceById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return issueInvoiceRestSupport.findIssueInvoiceById(id, user);
    }

    @ApiOperation(value = "查看收票列表")
    @RequestMapping(value = "/receiveInvoices", method = RequestMethod.GET)
    public Page<ReceiveInvoiceSummary> listReceiveInvoices(ReceiveInvoiceQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return receiveInvoiceRestSupport.listReceiveInvoices(request, user);
    }

    @ApiOperation(value = "查看收票")
    @RequestMapping(value = "/receiveInvoices/{id}", method = RequestMethod.GET)
    public ReceiveInvoiceDetail findReceiveInvoiceById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return receiveInvoiceRestSupport.findReceiveInvoiceById(id, user);
    }

    @ApiOperation(value = "查看项目回款")
    @RequestMapping(value = "/projectReceivedPayments/{id}", method = RequestMethod.GET)
    public ProjectReceivedPaymentDetail findProjectReceivedPaymentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectReceivedPaymentRestSupport.findProjectReceivedPaymentById(id, user);
    }

    @ApiOperation(value = "查看项目回款列表")
    @RequestMapping(value = "/projectReceivedPayments", method = RequestMethod.GET)
    public Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(
            ProjectReceivedPaymentQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return projectReceivedPaymentRestSupport.listProjectReceivedPayments(request, user);
    }

    @ApiOperation(value = "查看公司回款")
    @RequestMapping(value = "/companyReceivedPayments/{id}", method = RequestMethod.GET)
    public CompanyReceivedPaymentDetail findCompanyReceivedPaymentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return companyReceivedPaymentRestSupport.findCompanyReceivedPaymentById(id, user);
    }

    @ApiOperation(value = "查看公司回款列表")
    @RequestMapping(value = "/companyReceivedPayments", method = RequestMethod.GET)
    public Page<CompanyReceivedPaymentSummary> listCompanyReceivedPayments(
            CompanyReceivedPaymentQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return companyReceivedPaymentRestSupport.listCompanyReceivedPayments(request, user);
    }

    @ApiOperation(value = "查看待付款")
    @RequestMapping(value = "/pendingPayments", method = RequestMethod.GET)
    public Page<PendingPaymentSummary> listPendingPayments(PendingPaymentQueryParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return pendingPaymentRestSupport.listPendingPayments(request, user);
    }

    @ApiOperation(value = "查看证件管理（外管证）")
    @RequestMapping(value = "/licenses/{id}", method = RequestMethod.GET)
    public LicenseDetail findLicenseById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return licenseRestSupport.findLicenseById(id, user);
    }

    @ApiOperation(value = "查看保证金")
    @RequestMapping(value = "/deposits/{id}", method = RequestMethod.GET)
    public DepositDetail findDepositById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return depositRestSupport.findDepositById(id, user);
    }

    @ApiOperation(value = "查看待付款")
    @RequestMapping(value = "/pendingPayments/{id}", method = RequestMethod.GET)
    public PendingPaymentDetail findPendingPaymentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return pendingPaymentRestSupport.findPendingPaymentById(id, user);
    }

    @ApiOperation(value = "查看付款")
    @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
    public PaymentDetail findPaymentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return paymentRestSupport.findPaymentById(id, user);
    }

    @ApiOperation(value = "查看竣工")
    @RequestMapping(value = "/projectCompletions/{id}", method = RequestMethod.GET)
    public ProjectCompletionDetail findProjectCompletionById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectCompletionRestSupport.findProjectCompletionById(id, user);
    }

    @ApiOperation(value = "查看结算")
    @RequestMapping(value = "/projectSettlements/{id}", method = RequestMethod.GET)
    public ProjectSettlementSummary findProjectSettlementById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectSettlementRestSupport.findProjectSettlementById(id, user);
    }

}
