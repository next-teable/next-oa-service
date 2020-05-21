package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ProjectAggregation;
import cn.com.starest.nextoa.project.domain.model.ProjectFinancialAggregation;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectMonitorRestSupport {

	/**
	 * @param queryParameter
	 * @param user
	 * @return project financial report
	 */
	Page<ProjectFinancialAggregation> getProjectFinancialReport(ProjectQueryParameter queryParameter, User user);

	ProjectDetail findProjectById(String id, User user);

	List<ContractSummary> listProjectContracts(String id, User user);

	List<OrderSummary> listProjectOrders(String id, User user);

	List<SubContractSummary> listProjectSubContracts(String id, User user);

	List<SubOrderSummary> listProjectSubOrders(String id, User user);

	List<IssueInvoiceSummary> listProjectIssueInvoices(String id, User user);

	List<ReceiveInvoiceSummary> listProjectReceiveInvoices(String id, User user);

	List<LicenseSummary> listProjectLicenses(String id, User user);

	List<DepositSummary> listProjectDeposits(String id, User user);

	Page<PaymentSummary> listProjectPayments(String id, PageQueryParameter queryParameter, User user);

	Page<ReimburseSummary> listProjectReimburses(String id, PageQueryParameter queryParameter, User user);

	Page<PendingPaymentSummary> listProjectPendingPayments(String id, PageQueryParameter queryParameter, User user);

	Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(String id,
																	PageQueryParameter queryParameter,
																	User user);

	Page<SalarySummary> listProjectSalaries(String id, PageQueryParameter queryParameter, User user);

	Page<SalarySummary> listProjectSalaries(String id,
											int year,
											int month,
											SalaryQueryParameter queryParameter,
											User user);

	List<SalaryAggregationByMonth> listSalaryAggregationByMonth(String id,
																PageQueryParameter queryParameter,
																User user);

	ProjectAggregation listProjectAggregation(String id, User user);

	void resortProjects(User user);

	void clearProjectSorts(User user);

	Page<ProjectCompletionSummary> listProjectCompletions(String id, User user);

	Page<ProjectSettlementSummary> listProjectSettlements(String id, User user);

    Page<ProjectFinancialAggregation> getProjectFinancialReportByYear(ProjectQueryParameter queryParameter, User user);

    List<OrderSummary> listProjectOrders(String id, int year, User user);
}
