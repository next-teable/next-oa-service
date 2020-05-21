package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.NamedPageQueryParameter;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.project.service.BizDepartmentService;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.ProjectService;
import cn.com.starest.nextoa.project.web.dto.ProjectQueryParameter;
import cn.com.starest.nextoa.project.web.support.FinancialReportRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class FinancialReportRestSupportImpl implements FinancialReportRestSupport {

    @Autowired
    private FinancialReportService financialReportService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private BizDepartmentService bizDepartmentService;

    @Override
    public List<CompanyFinancialReport> getCompanyFinancialReport(User user) {
        return baseDataService.listSupervisedCompanies(new NamedPageQueryParameter(0, 50), user)
                .getContent()
                .stream()
                .map(company -> {
                    CompanyFinancialReport item = new CompanyFinancialReport();
                    item.setCompanyId(company.getId());
                    item.setCompanyName(company.getShortName());

                    List<CompanyFinancialAggregation> companyFinancialAggregationList = financialReportService
                            .calculateCompanyFinancialReport(company);
                    item.setItems(companyFinancialAggregationList);
                    item.setSum(financialReportService.sumCompanyFinancialReport(
                            companyFinancialAggregationList));

                    return item;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CompanyCapitalReport getCompanyCapitalReport(String companyId, int year, User user) {
        Company company = baseDataService.findCompanyById(companyId);
        if (company == null) {
            throw new EntityNotFoundException("无效的公司id");
        }
        CompanyCapitalReport result = new CompanyCapitalReport();
        result.setCompanyId(company.getId());
        result.setCompanyName(company.getShortName());
        List<CompanyCapitalAggregation> companyCapitalAggregationList = financialReportService.calculateCompanyCapitalReport(
                company,
                year);
        result.setItems(companyCapitalAggregationList);
        return result;
    }

    @Override
    public List<BusinessBizDepartmentFinancialAggregation> getBusinessBizDepartmentFinancialReport(String id, User user) {
        List<ProjectManageRelationship> relationshipList = projectService.listSupervisedProjectManageRelationship(id);
        if (relationshipList.isEmpty()) {
            return new ArrayList<>();
        }
        BizDepartment bizDepartment = relationshipList.get(0).getBelongTo();
        //计算项目相关的经费
        List<ProjectFinancialAggregation> projectFinancialReportList = relationshipList
                .stream()
                .map(relationship -> financialReportService
                        .calculateProjectFinancialReport(
                                relationship.getProject(),
                                user, relationship.getYear()))
                .collect(Collectors.toList());

        Map<Integer, BusinessBizDepartmentFinancialAggregation> byYear = new HashMap<>();
        for (ProjectFinancialAggregation projectFinancialAggregation : projectFinancialReportList) {
            BusinessBizDepartmentFinancialAggregation target = byYear.get(projectFinancialAggregation.getYear());
            if (null == target) {
                target = initBusinessBizDepartmentFinancialAggregation(projectFinancialAggregation.getYear(), bizDepartment);
                byYear.put(projectFinancialAggregation.getYear(), target);
            }
            target.getProjectFinancialAggregation().setOrderAmount(target.getProjectFinancialAggregation().getOrderAmount().add(projectFinancialAggregation.getOrderAmount()));
            target.getProjectFinancialAggregation().setIssueInvoiceAmount(target.getProjectFinancialAggregation().getIssueInvoiceAmount().add(projectFinancialAggregation.getIssueInvoiceAmount()));
            target.getProjectFinancialAggregation().setReceivedPaymentAmount(target.getProjectFinancialAggregation().getReceivedPaymentAmount().add(projectFinancialAggregation.getReceivedPaymentAmount()));
        }

        //计算部门自己的经费
        List<BizDepartmentFinancialAggregation> bizDepartmentFinancialAggregationList
                = financialReportService.calculateBizDepartmentFinancialReport(bizDepartment);
        for (BizDepartmentFinancialAggregation bizDepartmentFinancialAggregation : bizDepartmentFinancialAggregationList) {
            BusinessBizDepartmentFinancialAggregation target = byYear.get(bizDepartmentFinancialAggregation.getYear());
            if (null == target) {
                target = initBusinessBizDepartmentFinancialAggregation(bizDepartmentFinancialAggregation.getYear(), bizDepartment);
                byYear.put(bizDepartmentFinancialAggregation.getYear(), target);
            }

            target.getDepartmentFinancialAggregation()
                    .setActualCost(
                            target.getDepartmentFinancialAggregation().getActualCost().add(bizDepartmentFinancialAggregation.getActualCost()));
        }


        return new ArrayList<>(byYear.values());
    }

    private BusinessBizDepartmentFinancialAggregation initBusinessBizDepartmentFinancialAggregation(int year, BizDepartment bizDepartment) {
        BusinessBizDepartmentFinancialAggregation target = new BusinessBizDepartmentFinancialAggregation();
        target.setYear(year);
        target.setProjectFinancialAggregation(new ProjectFinancialAggregation());
        target.getProjectFinancialAggregation().setOrderAmount(BigDecimal.ZERO);
        target.getProjectFinancialAggregation().setIssueInvoiceAmount(BigDecimal.ZERO);
        target.getProjectFinancialAggregation().setReceivedPaymentAmount(BigDecimal.ZERO);

        target.setBizDepartmentId(bizDepartment.getId());
        target.setBizDepartmentName(bizDepartment.getName());

        target.setDepartmentFinancialAggregation(new BizDepartmentFinancialAggregation());
        target.getDepartmentFinancialAggregation().setActualCost(BigDecimal.ZERO);
        return target;
    }

    @Override
    public List<BizDepartmentFinancialReport> getBizDepartmentFinancialReport(User user) {
        List<BizDepartmentFinancialAggregation> bizDepartmentFinancialAggregationList = new ArrayList<>();

        bizDepartmentService.listSupervisedBizDepartments(new PageQueryParameter(0, 50), user)
                .getContent()
                .stream()
                .forEach(department -> bizDepartmentFinancialAggregationList.addAll(financialReportService.calculateBizDepartmentFinancialReport(
                        department)));

        Map<Integer, BizDepartmentFinancialReport> bizDepartmentFinancialReportByYear = new HashMap<>();

        for (BizDepartmentFinancialAggregation bizDepartmentFinancialAggregation : bizDepartmentFinancialAggregationList) {
            int year = bizDepartmentFinancialAggregation.getYear();
            BizDepartmentFinancialReport bizDepartmentFinancialReport = bizDepartmentFinancialReportByYear.get(year);
            if (bizDepartmentFinancialReport == null) {
                bizDepartmentFinancialReport = new BizDepartmentFinancialReport();
                bizDepartmentFinancialReport.setYear(year);
                bizDepartmentFinancialReportByYear.put(year, bizDepartmentFinancialReport);
            }

            bizDepartmentFinancialReport.getItems().add(bizDepartmentFinancialAggregation);
        }

        List<BizDepartmentFinancialReport> result = new ArrayList<>();
        result.addAll(bizDepartmentFinancialReportByYear.values());
        Collections.sort(result, (from, to) -> (to.getYear() - from.getYear()));
        for (BizDepartmentFinancialReport report : result) {
            BizDepartmentFinancialAggregation sum = new BizDepartmentFinancialAggregation();
            sum.setActualCost(BigDecimal.ZERO);
            sum.setBudgetCost(BigDecimal.ZERO);
            sum.setAvailableCost(BigDecimal.ZERO);
            sum.setTotalPay(BigDecimal.ZERO);
            sum.setTotalSalary(BigDecimal.ZERO);
            for (BizDepartmentFinancialAggregation bizDepartmentFinancialAggregation : report.getItems()) {
                sum.setActualCost(sum.getActualCost().add(bizDepartmentFinancialAggregation.getActualCost()));
                sum.setBudgetCost(sum.getBudgetCost().add(bizDepartmentFinancialAggregation.getBudgetCost()));
                sum.setAvailableCost(sum.getAvailableCost().add(bizDepartmentFinancialAggregation.getAvailableCost()));
                sum.setTotalPay(sum.getTotalPay().add(bizDepartmentFinancialAggregation.getTotalPay()));
                sum.setTotalSalary(sum.getTotalSalary().add(bizDepartmentFinancialAggregation.getTotalSalary()));
                sum.setBeyondBudget(sum.getActualCost().compareTo(sum.getAvailableCost()) > 0);
            }
            report.setSum(sum);
        }
        return result;
    }

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

        return new PageImpl<>(projectFinancialReportList,
                new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
                projectPage.getTotalElements());
    }


}
