package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.exception.ReportException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.FinancialYear;
import cn.com.starest.nextoa.project.service.utils.FinancialDateUtils;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author dz
 */
@Service
public class FinancialReportServiceImpl implements FinancialReportService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private LendingRepository lendingRepository;

    @Autowired
    private PendingPaymentRepository pendingPaymentRepository;

    @Autowired
    private IssueInvoiceRepository issueInvoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private BizDepartmentBudgetRepository bizDepartmentBudgetRepository;

    @Autowired
    private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

    @Autowired
    private CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

    @Autowired
    private CompanyCapitalRepository companyCapitalRepository;

    @Autowired
    private ProjectSettlementRepository projectProfitSettingRepository;

    @Autowired
    private SystemSettingService systemSettingService;

    @Override
    public List<CompanyFinancialAggregation> calculateCompanyFinancialReport(Company company) {
        FinancialYear[] years = FinancialDateUtils.getYearsForReport();
        List<CompanyFinancialAggregation> result = new ArrayList<>();
        for (FinancialYear year : years) {
            result.add(calculateCompanyFinancialReport(company, year));
        }
        return result;
    }

    @Override
    public CompanyFinancialAggregation calculateCompanyFinancialReport(Company company, FinancialYear year) {
        CompanyFinancialAggregation result = new CompanyFinancialAggregation();
        result.setCompanyId(company.getId());
        result.setCompanyName(company.getShortName());
        result.setYear(year.getYear());

        //全年收入 = 项目回款+公司回款
        result.setIncomeAmount(projectReceivedPaymentRepository.calculateTotalAmount(company, year.getYear())
                .add(companyReceivedPaymentRepository.calculateTotalAmount(
                        company,
                        year.getYear())));

        //项目支出 ( 项目付款 + 工资 )
        result.setProjectExpenseAmount(paymentRepository.calculateProjectTotalAmount(company, year.getYear())
                .add(salaryRepository.calculateProjectTotalPay(company,
                        year.getYear())));

        //公司运营支出 (公司付款 + 工资)
        result.setOperationExpenseAmount(paymentRepository.calculateTotalAmount(company, year.getYear())
                .add(salaryRepository.calculateBizDepartmentTotalPay(company,
                        year.getYear())));

        //员工借款（只累计最后一年）
        if (year.isCurrent()) {
            result.setEmployeeLendingAmount(lendingRepository.calculateLendingAmountByFrom(company)
                    .add(lendingRepository.calculateRepaymentAmountByFrom(
                            company))
                    .abs());
        } else {
            result.setEmployeeLendingAmount(BigDecimal.ZERO);
        }

        //保证金 （只累计最后一年）
        if (year.isCurrent()) {
            result.setDepositAmount(depositRepository.calculateTotalTransferAmount(company)
                    .subtract(depositRepository.calculateTotalReturnedAmount(company)));
        } else {
            result.setDepositAmount(BigDecimal.ZERO);
        }

        //待支付
        result.setPendingPaymentAmount(pendingPaymentRepository.calculateTotalAmount(company, year.getYear()));

        //合计支出
        result.setTotalAmount(result.getProjectExpenseAmount()
                .add(result.getOperationExpenseAmount())
                .add(result.getEmployeeLendingAmount())
                .add(result.getDepositAmount()));

        //余额 (收入 - 合计支出)
        result.setBalanceAmount(result.getIncomeAmount().subtract(result.getTotalAmount()));

        return result;
    }

    @Override
    public CompanyFinancialAggregation sumCompanyFinancialReport(List<CompanyFinancialAggregation> companyFinancialReportList) {
        CompanyFinancialAggregation result = new CompanyFinancialAggregation();
        result.setIncomeAmount(BigDecimal.ZERO);
        result.setProjectExpenseAmount(BigDecimal.ZERO);
        result.setOperationExpenseAmount(BigDecimal.ZERO);
        result.setEmployeeLendingAmount(BigDecimal.ZERO);
        result.setDepositAmount(BigDecimal.ZERO);
        result.setPendingPaymentAmount(BigDecimal.ZERO);
        result.setTotalAmount(BigDecimal.ZERO);
        result.setBalanceAmount(BigDecimal.ZERO);

        for (CompanyFinancialAggregation companyFinancialReport : companyFinancialReportList) {
            result.setCompanyId(companyFinancialReport.getCompanyId());
            result.setCompanyName(companyFinancialReport.getCompanyName());

            result.setIncomeAmount(result.getIncomeAmount().add(companyFinancialReport.getIncomeAmount()));
            result.setProjectExpenseAmount(result.getProjectExpenseAmount()
                    .add(companyFinancialReport.getProjectExpenseAmount()));
            result.setOperationExpenseAmount(result.getOperationExpenseAmount()
                    .add(companyFinancialReport.getOperationExpenseAmount()));
            result.setEmployeeLendingAmount(result.getEmployeeLendingAmount()
                    .add(companyFinancialReport.getEmployeeLendingAmount()));
            result.setDepositAmount(result.getDepositAmount().add(companyFinancialReport.getDepositAmount()));
            result.setPendingPaymentAmount(result.getPendingPaymentAmount()
                    .add(companyFinancialReport.getPendingPaymentAmount()));
            result.setTotalAmount(result.getTotalAmount().add(companyFinancialReport.getTotalAmount()));
            result.setBalanceAmount(result.getBalanceAmount().add(companyFinancialReport.getBalanceAmount()));
        }

        return result;
    }

    @Override
    public List<BizDepartmentFinancialAggregation> calculateBizDepartmentFinancialReport(BizDepartment department) {
        FinancialYear[] years = FinancialDateUtils.getYearsForReport();
        List<BizDepartmentFinancialAggregation> result = new ArrayList<>();
        for (FinancialYear year : years) {
            result.add(calculateBizDepartmentFinancialReport(department, year));
        }
        return result;
    }

    @Override
    public BizDepartmentFinancialAggregation calculateBizDepartmentFinancialReport(BizDepartment bizDepartment,
                                                                                   FinancialYear year) {
        BizDepartmentFinancialAggregation result = new BizDepartmentFinancialAggregation();
        result.setYear(year.getYear());
        result.setBizDepartmentId(bizDepartment.getId());
        result.setBizDepartmentName(bizDepartment.getName());

        result.setBudgetCost(BigDecimal.ZERO);
        result.setAvailableCost(BigDecimal.ZERO);
        BizDepartmentBudget bizDepartmentBudget = bizDepartmentBudgetRepository.findFirstByBizDepartmentAndYear(
                bizDepartment,
                year.getYear());
        if (bizDepartmentBudget != null) {
            result.setBudgetCost(bizDepartmentBudget.getBudget());

            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            result.setAvailableCost(year.isCurrent() ?
                    bizDepartmentBudget.getBudget()
                            .divide(new BigDecimal(12), 3, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(month))
                            .setScale(2, BigDecimal.ROUND_HALF_UP) :
                    bizDepartmentBudget.getBudget());
        }

        result.setTotalPay(paymentRepository.calculateTotalAmount(bizDepartment, year.getYear()));
        result.setTotalSalary(salaryRepository.calculateBizDepartmentTotalPay(bizDepartment, year.getYear()));

        result.setActualCost(paymentRepository.calculateTotalAmount(bizDepartment, year.getYear())
                .add(salaryRepository.calculateBizDepartmentTotalPay(bizDepartment,
                        year.getYear())));

        result.setBeyondBudget(result.getActualCost().compareTo(result.getAvailableCost()) > 0);
        return result;
    }

    @Override
    public ProjectFinancialAggregation calculateProjectFinancialReport(Project project, User byWho) {
        Company company = project.getCompany();
        if (company == null) {
            throw new ReportException(String.format("数据错误:项目「%s」没有对应的公司", project.getName()));
        }

        ProjectFinancialAggregation result = new ProjectFinancialAggregation();
        result.setCompanyId(company.getId());
        result.setCompanyName(company.getShortName());
        result.setProjectId(project.getId());
        result.setProjectName(project.getName());
        result.setProvince(project.getProvince());
        result.setCity(project.getCity());
        result.setOutputProfitRateThreshold(project.getOutputProfitRate());
        result.setCollectProfitRateThreshold(project.getCollectProfitRate());

        //合同金额：项目关联的合同金额之和
        result.setContractAmount(contractRepository.calculateTotalAmount(project));

        //订单金额：项目关联的主订单金额总和
        result.setOrderAmount(orderRepository.calculateTotalAmount(project));

        //回款金额：项目回款中回款类型为项目回款的总和
        result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(project));

        //项目成本=付款模块中项目运营支出部分 + 工资 + 待支付
        result.setCostAmount(paymentRepository.calculateTotalAmount(project)
                .add(salaryRepository.calculateProjectTotalPay(project))
                .add(pendingPaymentRepository.calculateTotalAmount(project)));

        //产值利润=订单金额-项目成本
        result.setOutputProfitAmount(result.getOrderAmount().subtract(result.getCostAmount()));

        //产值利润率=产值利润/订单金额
        if (result.getOrderAmount().compareTo(BigDecimal.ZERO) == 0) {
            result.setOutputProfitRate(BigDecimal.ZERO);
        } else {
            result.setOutputProfitRate(result.getOutputProfitAmount()
                    .multiply(new BigDecimal(100))
                    .divide(result.getOrderAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        //回款利润=回款金额-项目成本
        result.setCollectProfitAmount(result.getReceivedPaymentAmount().subtract(result.getCostAmount()));

        //回款利润率=回款利润/回款金额
        if (result.getReceivedPaymentAmount().compareTo(BigDecimal.ZERO) == 0) {
            result.setCollectProfitRate(BigDecimal.ZERO);
        } else {
            result.setCollectProfitRate(result.getCollectProfitAmount()
                    .multiply(new BigDecimal(100))
                    .divide(result.getReceivedPaymentAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        //应收金额=开票金额-回款金额
        result.setReceivableAmount(issueInvoiceRepository.calculateTotalAmount(project)
                .subtract(result.getReceivedPaymentAmount()));

        //待付款金额
        result.setPendingPaymentAmount(pendingPaymentRepository.calculateTotalAmount(project));

        if (byWho == null) {
            return result;
        }

        //profit
        ProjectSettlement projectProfitSetting = projectProfitSettingRepository.findFirstByProject(project);
        if (projectProfitSetting != null &&
                projectProfitSetting.getUser1() != null &&
                projectProfitSetting.getUser1().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent1() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent1());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser2() != null &&
                projectProfitSetting.getUser2().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent2() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent2());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser3() != null &&
                projectProfitSetting.getUser3().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent3() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent3());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser4() != null &&
                projectProfitSetting.getUser4().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent4() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent4());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser5() != null &&
                projectProfitSetting.getUser5().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent5() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent5());
            return result;
        }

        return result;
    }

    @Override
    public ProjectFinancialAggregation calculateProjectFinancialReport(Project project, User byWho, int year) {
        Company company = project.getCompany();
        if (company == null) {
            throw new ReportException(String.format("数据错误:项目「%s」没有对应的公司", project.getName()));
        }

        ProjectFinancialAggregation result = new ProjectFinancialAggregation();
        result.setCompanyId(company.getId());
        result.setCompanyName(company.getShortName());
        result.setProjectId(project.getId());
        result.setProjectName(project.getName());
        result.setProvince(project.getProvince());
        result.setCity(project.getCity());
        result.setOutputProfitRateThreshold(project.getOutputProfitRate());
        result.setCollectProfitRateThreshold(project.getCollectProfitRate());

        //按年的统计不计算合同金额 20180102
//        result.setContractAmount(contractRepository.calculateTotalAmount(project));

        //订单金额：项目关联的主订单金额总和
        result.setOrderAmount(orderRepository.calculateTotalAmount(project, year));

        //回款金额：项目回款中回款类型为项目回款的总和
        result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(project, year));

        //项目成本=付款模块中项目运营支出部分 + 工资 + 待支付
        result.setCostAmount(paymentRepository.calculateTotalAmount(project, year)
                .add(salaryRepository.calculateProjectTotalPay(project, year))
                .add(pendingPaymentRepository.calculateTotalAmount(project, year)));

        //产值利润=订单金额-项目成本
        result.setOutputProfitAmount(result.getOrderAmount().subtract(result.getCostAmount()));

        //产值利润率=产值利润/订单金额
        if (result.getOrderAmount().compareTo(BigDecimal.ZERO) == 0) {
            result.setOutputProfitRate(BigDecimal.ZERO);
        } else {
            result.setOutputProfitRate(result.getOutputProfitAmount()
                    .multiply(new BigDecimal(100))
                    .divide(result.getOrderAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        //回款利润=回款金额-项目成本
        result.setCollectProfitAmount(result.getReceivedPaymentAmount().subtract(result.getCostAmount()));

        //回款利润率=回款利润/回款金额
        if (result.getReceivedPaymentAmount().compareTo(BigDecimal.ZERO) == 0) {
            result.setCollectProfitRate(BigDecimal.ZERO);
        } else {
            result.setCollectProfitRate(result.getCollectProfitAmount()
                    .multiply(new BigDecimal(100))
                    .divide(result.getReceivedPaymentAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }
        //开票金额
        result.setIssueInvoiceAmount(issueInvoiceRepository.calculateTotalAmount(project, year));
        //应收金额=开票金额-回款金额
        result.setReceivableAmount(result.getIssueInvoiceAmount()
                .subtract(result.getReceivedPaymentAmount()));

        //待付款金额
        result.setPendingPaymentAmount(pendingPaymentRepository.calculateTotalAmount(project, year));

        if (byWho == null) {
            return result;
        }

        //profit
        ProjectSettlement projectProfitSetting = projectProfitSettingRepository.findFirstByProject(project);
        if (projectProfitSetting != null &&
                projectProfitSetting.getUser1() != null &&
                projectProfitSetting.getUser1().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent1() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent1());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser2() != null &&
                projectProfitSetting.getUser2().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent2() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent2());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser3() != null &&
                projectProfitSetting.getUser3().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent3() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent3());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser4() != null &&
                projectProfitSetting.getUser4().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent4() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent4());
            return result;
        }

        if (projectProfitSetting != null &&
                projectProfitSetting.getUser5() != null &&
                projectProfitSetting.getUser5().getId().equals(byWho.getId()) &&
                projectProfitSetting.getPercent5() != null) {
            result.setProfitPercent(projectProfitSetting.getPercent5());
            return result;
        }
        result.setYear(year);
        return result;
    }

    @Override
    public List<CompanyCapitalAggregation> calculateCompanyCapitalReport(Company company, int year) {
        List<CompanyCapitalAggregation> result = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            if (FinancialDateUtils.isFuture(year, month)) {
                result.add(CompanyCapitalAggregation.from(company, year, month));
            } else {
                result.add(calculateCompanyCapitalReport(company, year, month));
            }
        }
        return result;
    }

    @Override
    public CompanyCapitalAggregation calculateCompanyCapitalReport(Company company, int year, int month) {
        BigDecimal initialCapitalAmount = BigDecimal.ZERO;
        CompanyCapital companyCapital = companyCapitalRepository.findFirstByCompanyAndYearAndMonth(company,
                year,
                month);
        if (companyCapital != null && companyCapital.getAmount() != null) {
            initialCapitalAmount = companyCapital.getAmount();
        }

        CompanyCapitalAggregation result = CompanyCapitalAggregation.from(company, year, month);

        //初期现金余额
        result.setInitialCapitalAmount(initialCapitalAmount);

        //项目收入
        result.setProjectIncomeAmount(projectReceivedPaymentRepository.calculateTotalAmount(company, year, month));

        //公司运营收入
        result.setCompanyIncomeAmount(companyReceivedPaymentRepository.calculateTotalAmount(company, year, month));

        //收入合计
        result.setIncomeTotalAmount(result.getProjectIncomeAmount().add(result.getCompanyIncomeAmount()));

        //员工工资
        result.setSalaryAmount(salaryRepository.calculateTotalPay(company, year, month));

        //公司运营费支出
        result.setBizDepartmentPaymentAmount(paymentRepository.calculateBizDepartmentTotalAmount(company, year, month));

        //项目运营支出
        result.setProjectPaymentAmount(paymentRepository.calculateProjectTotalAmount(company, year, month));

        //支出合计
        result.setPaymentTotalAmount(result.getSalaryAmount()
                .add(result.getBizDepartmentPaymentAmount())
                .add(result.getProjectPaymentAmount()));

        //现金余额
        result.setReservedCashAmount(result.getInitialCapitalAmount()
                .add(result.getIncomeTotalAmount())
                .subtract(result.getPaymentTotalAmount()));

        //借还款情况
        result.setLendingBalanceAmount(lendingRepository.calculateLendingAmountByFrom(company, year, month)
                .add(lendingRepository.calculateRepaymentAmountByFrom(company,
                        year,
                        month)));

        //保证金
        result.setDepositAmount(depositRepository.calculateTotalTransferAmount(company, year, month)
                .subtract(depositRepository.calculateTotalReturnedAmount(company,
                        year,
                        month)));

        //账面现金余额
        result.setAccountCashAmount(result.getReservedCashAmount()
                .add(result.getLendingBalanceAmount())
                .subtract(result.getDepositAmount()));

        //待支付
        if (FinancialDateUtils.isNow(year, month)) {
            result.setPendingPaymentAmount(pendingPaymentRepository.calculateTotalAmount(company, year, month));
        } else {
            result.setPendingPaymentAmount(BigDecimal.ZERO);
        }

        //可用现金余额
        result.setAvailableCashAmount(result.getAccountCashAmount().subtract(result.getPendingPaymentAmount()));

        return result;
    }

    @Override
    public ProjectSettlementAggregation calculateProjectSettlementAggregation(ProjectSettlement projectSettlement) {
        if (projectSettlement == null) {
            return null;
        }

        ProjectSettlementAggregation result = ProjectSettlementAggregation.from(projectSettlement);

        Order order = projectSettlement.getOrder();
        if (order != null) {
            //如果订单不为空,则以订单为准来计算回款金额
            result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(order));
        }

        Contract contract = projectSettlement.getContract();
        if (contract != null) {
            //如果订单为空,则以合同为准来计算回款金额
            result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(contract));
        }

        if (result.getPercent1() != null) {
            result.setProfit1(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent1()));
            if (result.getMarketPercent1() != null) {
                result.setMarketProfit1(result.getReceivedPaymentAmount()
                        .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                        .multiply(result.getMarketPercent1()));
            }
            result.setWithdrawAmount1(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser1Id()));
            result.setLeftAmount1(result.getProfit1()
                    .add(result.getMarketProfit1())
                    .subtract(result.getWithdrawAmount1()));
        }

        if (result.getPercent2() != null) {
            result.setProfit2(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent2()));
            if (result.getMarketPercent2() != null) {
                result.setMarketProfit2(result.getReceivedPaymentAmount()
                        .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                        .multiply(result.getMarketPercent2()));
            }
            result.setWithdrawAmount2(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser2Id()));
            result.setLeftAmount2(result.getProfit2()
                    .add(result.getMarketProfit2())
                    .subtract(result.getWithdrawAmount2()));
        }

        if (result.getPercent3() != null) {
            result.setProfit3(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent3()));
            if (result.getMarketPercent3() != null) {
                result.setMarketProfit3(result.getReceivedPaymentAmount()
                        .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                        .multiply(result.getMarketPercent3()));
            }
            result.setWithdrawAmount3(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser3Id()));
            result.setLeftAmount3(result.getProfit3()
                    .add(result.getMarketProfit3())
                    .subtract(result.getWithdrawAmount3()));
        }

        if (result.getPercent4() != null) {
            result.setProfit4(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent4()));
            result.setWithdrawAmount4(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser4Id()));
            result.setLeftAmount4(result.getProfit4().subtract(result.getWithdrawAmount4()));
        }

        if (result.getPercent5() != null) {
            result.setProfit5(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent5()));
            result.setWithdrawAmount5(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser5Id()));
            result.setLeftAmount5(result.getProfit5().subtract(result.getWithdrawAmount5()));
        }

        return result;
    }

    @Override
    public ProjectSettlementAggregation calculateProjectSettlementAggregationByOneUser(ProjectSettlement projectSettlement,
                                                                                       User byWho) {
        if (projectSettlement == null) {
            return null;
        }

        ProjectSettlementAggregation result = ProjectSettlementAggregation.fromOneUser(projectSettlement, byWho);

        Order order = projectSettlement.getOrder();
        if (order != null) {
            //如果订单不为空,则以订单为准来计算回款金额
            result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(order));
        }

        Contract contract = projectSettlement.getContract();
        if (contract != null) {
            //如果订单为空,则以合同为准来计算回款金额
            result.setReceivedPaymentAmount(projectReceivedPaymentRepository.calculateTotalAmount(contract));
        }

        if (result.getPercent1() != null && byWho.getId().equals(result.getUser1Id())) {
            result.setProfit1(result.getReceivedPaymentAmount()
                    .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                    .multiply(result.getPercent1()));
            if (result.getMarketPercent1() != null) {
                result.setMarketProfit1(result.getReceivedPaymentAmount()
                        .divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP)
                        .multiply(result.getMarketPercent1()));
            }
            result.setWithdrawAmount1(paymentRepository.calculateTotalAmount(projectSettlement.getId(),
                    result.getUser1Id()));
            result.setLeftAmount1(result.getProfit1()
                    .add(result.getMarketProfit1())
                    .subtract(result.getWithdrawAmount1()));
        }

        return result;
    }

    @Override
    public ProjectFinancialAggregation sumProjectFinancialReport(ProjectQueryRequest request, User byWho) {
        //if the user is global supervisors, the user can query all the projects
        boolean supervisor = User.ADMINISTRATOR.equals(byWho.getUsername());
        if (!supervisor) {
            supervisor = systemSettingService.isProjectSupervisor(byWho);
        }

        Page<Project> projectPage = supervisor ?
                projectRepository.queryPage4SupervisedMonitorProject(request) :
                projectRepository.queryPage4ManagedMonitorProject(request, byWho);

        ProjectFinancialAggregation result = new ProjectFinancialAggregation();

        //合同金额：项目关联的合同金额之和
        result.setContractAmount(BigDecimal.ZERO);
        //订单金额：项目关联的主订单金额总和
        result.setOrderAmount(BigDecimal.ZERO);
        //回款金额：项目回款中回款类型为项目回款的总和
        result.setReceivedPaymentAmount(BigDecimal.ZERO);
        //项目成本=付款模块中项目运营支出部分 + 工资 + 待支付
        result.setCostAmount(BigDecimal.ZERO);
        //产值利润=订单金额-项目成本
        result.setOutputProfitAmount(BigDecimal.ZERO);
        //产值利润率=产值利润/订单金额
        result.setOutputProfitRate(BigDecimal.ZERO);
        //回款利润=回款金额-项目成本
        result.setCollectProfitAmount(BigDecimal.ZERO);
        //回款利润率=回款利润/回款金额
        result.setCollectProfitRate(BigDecimal.ZERO);
        //应收金额=开票金额-回款金额
        result.setReceivableAmount(BigDecimal.ZERO);
        //待付款金额
        result.setPendingPaymentAmount(BigDecimal.ZERO);


        for (Project project : projectPage.getContent()) {
            ProjectFinancialAggregation projectFinancialAggregation = calculateProjectFinancialReport(project, null);

            //合同金额：项目关联的合同金额之和
            result.setContractAmount(result.getContractAmount().add(projectFinancialAggregation.getContractAmount()));
            //订单金额：项目关联的主订单金额总和
            result.setOrderAmount(result.getOrderAmount().add(projectFinancialAggregation.getOrderAmount()));
            //回款金额：项目回款中回款类型为项目回款的总和
            result.setReceivedPaymentAmount(result.getReceivedPaymentAmount()
                    .add(projectFinancialAggregation.getReceivedPaymentAmount()));
            //项目成本=付款模块中项目运营支出部分 + 工资 + 待支付
            result.setCostAmount(result.getCostAmount().add(projectFinancialAggregation.getCostAmount()));
            //产值利润=订单金额-项目成本
            result.setOutputProfitAmount(result.getOutputProfitAmount()
                    .add(projectFinancialAggregation.getOutputProfitAmount()));
            //产值利润率=产值利润/订单金额
            result.setOutputProfitRate(result.getOutputProfitRate()
                    .add(projectFinancialAggregation.getOutputProfitRate()));
            //回款利润=回款金额-项目成本
            result.setCollectProfitAmount(result.getCollectProfitAmount()
                    .add(projectFinancialAggregation.getCollectProfitAmount()));
            //应收金额=开票金额-回款金额
            result.setReceivableAmount(result.getReceivableAmount()
                    .add(projectFinancialAggregation.getReceivableAmount()));
            //待付款金额
            result.setPendingPaymentAmount(result.getPendingPaymentAmount()
                    .add(projectFinancialAggregation.getPendingPaymentAmount()));
        }


        if (result.getReceivedPaymentAmount() != null && result.getReceivedPaymentAmount().compareTo(BigDecimal.ZERO) != 0) {
            result.setCollectProfitRate(result.getCollectProfitAmount()
                    .multiply(new BigDecimal(100))
                    .divide(result.getReceivedPaymentAmount(), 2, BigDecimal.ROUND_HALF_UP));
        }

        return result;
    }

}
