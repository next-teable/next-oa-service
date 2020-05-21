package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.exception.BizException;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentFinancialAggregation;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.model.Salary;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.service.*;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.BizDepartmentMonitorRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

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
public class BizDepartmentMonitorRestSupportImpl implements BizDepartmentMonitorRestSupport {

	@Autowired
	private BizDepartmentService bizDepartmentService;

	@Autowired
	private PaymentService paymentService;
	//
	//	@Autowired
	//	private ReimburseService reimburseService;
	//
	//	@Autowired
	//	private PendingPaymentService pendingPaymentService;

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private FinancialReportService financialReportService;

	@Override
	public Page<PaymentSummary> listBizDepartmentPayments(String id,
														  int year,
														  PageQueryParameter queryParameter,
														  User user) {
		PaymentQueryParameter paymentQueryParameter = new PaymentQueryParameter();
		paymentQueryParameter.setBizDepartmentId(id);
		paymentQueryParameter.setYear(year);
		paymentQueryParameter.setStart(queryParameter.getStart());
		paymentQueryParameter.setLimit(queryParameter.getLimit());
		Page<Payment> paymentPage = paymentService.listPayments(paymentQueryParameter, user);

		return new PageImpl<>(paymentPage.getContent().stream().map(PaymentSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
							  paymentPage.getTotalElements());
	}

	@Override
	public Page<SalarySummary> listBizDepartmentSalaries(String id,
														 int year,
														 PageQueryParameter queryParameter,
														 User user) {
		SalaryQueryParameter salaryQueryParameter = new SalaryQueryParameter();
		salaryQueryParameter.setBizDepartmentId(id);
		salaryQueryParameter.setYear(year);
		salaryQueryParameter.setStart(queryParameter.getStart());
		salaryQueryParameter.setLimit(queryParameter.getLimit());

		Page<Salary> salaryPage = salaryService.listSalaries(salaryQueryParameter, user);

		return new PageImpl<>(salaryPage.getContent().stream().map(SalarySummary::from).collect(Collectors.toList()),
							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
							  salaryPage.getTotalElements());
	}

	@Override
	public List<SalaryAggregationByMonth> listSalaryAggregationByMonth(String id,
																	   int year,
																	   PageQueryParameter queryParameter,
																	   User user) {
		BizDepartment bizDepartment = bizDepartmentService.findBizDepartmentById(id);
		if (bizDepartment == null) {
			return Collections.emptyList();
		}

		List<SalaryAggregationByMonth> result = new ArrayList<>();
		for (int month = 1; month <= 12; month++) {
			BigDecimal totalPay = salaryService.calculateBizDepartmentSalary(id, year, month);
			if (totalPay.compareTo(BigDecimal.ZERO) > 0) {
				SalaryAggregationByMonth item = new SalaryAggregationByMonth();
				item.setSubjectId(id);
				item.setSubjectName(bizDepartment.getName());
				item.setYear(year);
				item.setMonth(month);
				item.setTotalPay(totalPay);
				result.add(item);
			}
		}
		return result;
	}

	@Override
	public Page<SalarySummary> listBizDepartmentSalaries(String id,
														 int year,
														 int month,
														 PageQueryParameter queryParameter,
														 User user) {
		SalaryQueryParameter salaryQueryParameter = new SalaryQueryParameter();
		salaryQueryParameter.setBizDepartmentId(id);
		salaryQueryParameter.setYear(year);
		salaryQueryParameter.setMonth(month);
		salaryQueryParameter.setStart(queryParameter.getStart());
		salaryQueryParameter.setLimit(queryParameter.getLimit());

		Page<Salary> salaryPage = salaryService.listSalaries(salaryQueryParameter, user);

		return new PageImpl<>(salaryPage.getContent().stream().map(SalarySummary::from).collect(Collectors.toList()),
							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
							  salaryPage.getTotalElements());
	}

	//	@Override
	//	public Page<ReimburseSummary> listBizDepartmentReimburses(String id, PageQueryParameter queryParameter, User user) {
	//		ReimburseQueryParameter reimburseQueryParameter = new ReimburseQueryParameter();
	//		reimburseQueryParameter.setBizDepartmentId(id);
	//		reimburseQueryParameter.setStart(queryParameter.getStart());
	//		reimburseQueryParameter.setLimit(queryParameter.getLimit());
	//
	//		Page<Reimburse> reimbursePage = reimburseService.listReimburses(reimburseQueryParameter, user);
	//
	//		return new PageImpl<>(reimbursePage.getContent()
	//										   .stream()
	//										   .map(ReimburseSummary::from)
	//										   .collect(Collectors.toList()),
	//							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
	//							  reimbursePage.getTotalElements());
	//	}

	//	@Override
	//	public Page<PendingPaymentSummary> listBizDepartmentPendingPayments(String id,
	//																		PageQueryParameter queryParameter,
	//																		User user) {
	//		PendingPaymentQueryParameter pendingPaymentQueryParameter = new PendingPaymentQueryParameter();
	//		pendingPaymentQueryParameter.setBizDepartmentId(id);
	//		pendingPaymentQueryParameter.setStart(queryParameter.getStart());
	//		pendingPaymentQueryParameter.setLimit(queryParameter.getLimit());
	//
	//		Page<PendingPayment> pendingPaymentPage = pendingPaymentService.listPendingPayments(pendingPaymentQueryParameter,
	//																							user);
	//
	//		return new PageImpl<>(pendingPaymentPage.getContent()
	//												.stream()
	//												.map(PendingPaymentSummary::from)
	//												.collect(Collectors.toList()),
	//							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
	//							  pendingPaymentPage.getTotalElements());
	//	}

	@Override
	public BizDepartmentSummary findBizDepartmentById(String id, User user) {
		return BizDepartmentSummary.from(bizDepartmentService.findBizDepartmentById(id));
	}

	@Override
	public BizDepartmentFinancialAggregation getBizDepartmentAggregation(String id, int year, User user) {
		BizDepartment bizDepartment = bizDepartmentService.findBizDepartmentById(id);
		if (bizDepartment == null) {
			throw new EntityNotFoundException("无效的业务部门id");
		}

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		if (currentYear < year) {
			throw new BizException("请查询今年或者今年以前的数据");
		}

		return financialReportService.calculateBizDepartmentFinancialReport(bizDepartment,
																			new FinancialYear(year,
																							  year == currentYear));
	}

}
