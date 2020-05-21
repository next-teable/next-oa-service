package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.ProjectAggregation;
import cn.com.starest.nextoa.project.domain.model.ProjectFinancialAggregation;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ProjectMonitorRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("项目监管")
@RestController
@RequestMapping("/api/monitor")
public class ProjectMonitorRestController {

    @Autowired
    private ProjectMonitorRestSupport projectMonitorRestSupport;

    @ApiOperation(value = "查看项目财务报表(支持分页,不同身份的用户看到的数据量不一样,项目主管只能看到自己的项目,公司高管可以看到所有项目)")
    @RequestMapping(value = "/projects/financialReport", method = RequestMethod.GET)
    public Page<ProjectFinancialAggregation> getProjectFinancialReport(ProjectQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        Page<ProjectFinancialAggregation> result = projectMonitorRestSupport.getProjectFinancialReport(queryParameter, user);
        return result;
    }

    @ApiOperation(value = "查看小项目财务报表(支持分页,不同身份的用户看到的数据量不一样,项目主管只能看到自己的项目,公司高管可以看到所有项目)")
    @RequestMapping(value = "/projects/financialReport/byYear", method = RequestMethod.GET)
    public Page<ProjectFinancialAggregation> getProjectFinancialReportByYear(ProjectQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        Page<ProjectFinancialAggregation> result = projectMonitorRestSupport.getProjectFinancialReportByYear(queryParameter, user);
        return result;
    }

    @ApiOperation(value = "重新计算排序")
    @RequestMapping(value = "/projects/resort", method = RequestMethod.GET)
    public boolean sortProject() {
        User user = SecurityContexts.getContext().requireUser();
        projectMonitorRestSupport.resortProjects(user);
        return true;
    }

    @ApiOperation(value = "重新计算排序")
    @RequestMapping(value = "/projects/clearSort", method = RequestMethod.GET)
    public boolean clearProjectSorts() {
        User user = SecurityContexts.getContext().requireUser();
        projectMonitorRestSupport.clearProjectSorts(user);
        return true;
    }

    @ApiOperation(value = "查看项目")
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ProjectDetail findProjectById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.findProjectById(id, user);
    }

    @ApiOperation(value = "查看项目汇总统计")
    @RequestMapping(value = "/projects/{id}/aggregation", method = RequestMethod.GET)
    public ProjectAggregation listProjectAggregation(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectAggregation(id, user);
    }

    @ApiOperation(value = "查看项目合同")
    @RequestMapping(value = "/projects/{id}/contracts", method = RequestMethod.GET)
    public List<ContractSummary> listProjectContracts(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectContracts(id, user);
    }

    @ApiOperation(value = "查看项目订单")
    @RequestMapping(value = "/projects/{id}/orders", method = RequestMethod.GET)
    public List<OrderSummary> listProjectOrders(@PathVariable String id, int year) {
        User user = SecurityContexts.getContext().requireUser();
        if (year == 0) {
            return projectMonitorRestSupport.listProjectOrders(id, user);
        } else {
            return projectMonitorRestSupport.listProjectOrders(id, year, user);
        }
    }

    @ApiOperation(value = "查看项目分包合同")
    @RequestMapping(value = "/projects/{id}/subContracts", method = RequestMethod.GET)
    public List<SubContractSummary> listProjectSubContracts(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectSubContracts(id, user);
    }

    @ApiOperation(value = "查看项目分包订单")
    @RequestMapping(value = "/projects/{id}/subOrders", method = RequestMethod.GET)
    public List<SubOrderSummary> listProjectSubOrders(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectSubOrders(id, user);
    }

    @ApiOperation(value = "查看项目开票")
    @RequestMapping(value = "/projects/{id}/issueInvoices", method = RequestMethod.GET)
    public List<IssueInvoiceSummary> listProjectIssueInvoices(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectIssueInvoices(id, user);
    }

    @ApiOperation(value = "查看项目收票")
    @RequestMapping(value = "/projects/{id}/receiveInvoices", method = RequestMethod.GET)
    public List<ReceiveInvoiceSummary> listProjectReceiveInvoices(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectReceiveInvoices(id, user);
    }

    @ApiOperation(value = "查看项目外管证")
    @RequestMapping(value = "/projects/{id}/licenses", method = RequestMethod.GET)
    public List<LicenseSummary> listProjectLicenses(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectLicenses(id, user);
    }

    @ApiOperation(value = "查看项目保证金")
    @RequestMapping(value = "/projects/{id}/deposits", method = RequestMethod.GET)
    public List<DepositSummary> listProjectDeposits(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectDeposits(id, user);
    }

    @ApiOperation(value = "查看待付款")
    @RequestMapping(value = "/projects/{id}/pendingPayments", method = RequestMethod.GET)
    public Page<PendingPaymentSummary> listProjectPendingPayments(@PathVariable String id,
                                                                  PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectPendingPayments(id, queryParameter, user);
    }


    @ApiOperation(value = "查看项目回款")
    @RequestMapping(value = "/projects/{id}/receivedPayments", method = RequestMethod.GET)
    public Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(@PathVariable String id,
                                                                           PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectReceivedPayments(id, queryParameter, user);
    }

    @ApiOperation(value = "查看项目付款")
    @RequestMapping(value = "/projects/{id}/payments", method = RequestMethod.GET)
    public Page<PaymentSummary> listProjectPayments(@PathVariable String id, PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectPayments(id, queryParameter, user);
    }

    @ApiOperation(value = "查看报销")
    @RequestMapping(value = "/projects/{id}/reimburses", method = RequestMethod.GET)
    public Page<ReimburseSummary> listProjectReimburses(@PathVariable String id, PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectReimburses(id, queryParameter, user);
    }

    @ApiOperation(value = "查询工资汇总 - 按项目按年按月汇总")
    @RequestMapping(value = "/projects/{id}/salaries/aggregationByMonth", method = RequestMethod.GET)
    public List<SalaryAggregationByMonth> listSalaryAggregationByMonth(@PathVariable String id,
                                                                       PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listSalaryAggregationByMonth(id, queryParameter, user);
    }

    @ApiOperation(value = "查看工资")
    @RequestMapping(value = "/projects/{id}/salaries", method = RequestMethod.GET)
    public Page<SalarySummary> listProjectSalaries(@PathVariable String id, PageQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectSalaries(id, queryParameter, user);
    }

    @ApiOperation(value = "查看工资")
    @RequestMapping(value = "/projects/{id}/{year}/{month}/salaries", method = RequestMethod.GET)
    public Page<SalarySummary> listProjectSalaries(@PathVariable String id,
                                                   @PathVariable int year,
                                                   @PathVariable int month,
                                                   SalaryQueryParameter queryParameter) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectSalaries(id, year, month, queryParameter, user);
    }

    @ApiOperation(value = "查看竣工列表")
    @RequestMapping(value = "/projects/{id}/projectCompletions", method = RequestMethod.GET)
    public Page<ProjectCompletionSummary> listProjectCompletions(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectCompletions(id, user);
    }

    @ApiOperation(value = "查看结算列表")
    @RequestMapping(value = "/projects/{id}/projectSettlements", method = RequestMethod.GET)
    public Page<ProjectSettlementSummary> listProjectSettlements(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        return projectMonitorRestSupport.listProjectSettlements(id, user);
    }

}
