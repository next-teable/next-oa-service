package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentFinancialAggregation;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentFinancialReport;
import cn.com.starest.nextoa.project.domain.model.BusinessBizDepartmentFinancialAggregation;
import cn.com.starest.nextoa.project.domain.model.ProjectFinancialAggregation;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentSummary;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SalaryAggregationByMonth;
import cn.com.starest.nextoa.project.web.dto.SalarySummary;
import cn.com.starest.nextoa.project.web.support.BizDepartmentMonitorRestSupport;
import cn.com.starest.nextoa.project.web.support.FinancialReportRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("业务部门监管")
@RestController
@RequestMapping("/api/monitor")
public class BizDepartmentMonitorRestController {

	@Autowired
	private BizDepartmentMonitorRestSupport bizDepartmentMonitorRestSupport;

	@Autowired
	private FinancialReportRestSupport financialReportRestSupport;

	@ApiOperation(value = "业务部门财务报表")
	@RequestMapping(value = "/bizDepartments/financialReport", method = RequestMethod.GET)
	public List<BizDepartmentFinancialReport> getBizDepartmentFinancialReport() {
		User user = SecurityContexts.getContext().requireUser();
		return financialReportRestSupport.getBizDepartmentFinancialReport(user);
	}

	@ApiOperation(value = "事业部门财务报表")
	@RequestMapping(value = "/bizDepartments/{id}/business/financialReport", method = RequestMethod.GET)
	public List<BusinessBizDepartmentFinancialAggregation> getBusinessBizDepartmentFinancialReport(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return financialReportRestSupport.getBusinessBizDepartmentFinancialReport(id, user);
	}

	@ApiOperation(value = "查看业务部门")
	@RequestMapping(value = "/bizDepartments/{id}", method = RequestMethod.GET)
	public BizDepartmentSummary findBizDepartmentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.findBizDepartmentById(id, user);
	}

	@ApiOperation(value = "查看业务部门")
	@RequestMapping(value = "/bizDepartments/{id}/{year}/aggregation", method = RequestMethod.GET)
	public BizDepartmentFinancialAggregation findBizDepartmentById(@PathVariable String id, @PathVariable int year) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.getBizDepartmentAggregation(id, year, user);
	}

	@ApiOperation(value = "查看业务部门付款")
	@RequestMapping(value = "/bizDepartments/{id}/{year}/payments", method = RequestMethod.GET)
	public Page<PaymentSummary> listBizDepartmentPayments(@PathVariable String id,
														  @PathVariable int year,
														  PageQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.listBizDepartmentPayments(id, year, queryParameter, user);
	}

	@ApiOperation(value = "查询工资汇总 - 按部门按年按月汇总")
	@RequestMapping(value = "/bizDepartments/{id}/{year}/salaries/aggregationByMonth", method = RequestMethod.GET)
	public List<SalaryAggregationByMonth> listSalaryAggregationByMonth(@PathVariable String id,
																	   @PathVariable int year,
																	   PageQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.listSalaryAggregationByMonth(id, year, queryParameter, user);
	}

	@ApiOperation(value = "查看工资")
	@RequestMapping(value = "/bizDepartments/{id}/{year}/salaries", method = RequestMethod.GET)
	public Page<SalarySummary> listBizDepartmentSalaries(@PathVariable String id,
														 @PathVariable int year,
														 PageQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.listBizDepartmentSalaries(id, year, queryParameter, user);
	}

	@ApiOperation(value = "查看工资")
	@RequestMapping(value = "/bizDepartments/{id}/{year}/{month}/salaries", method = RequestMethod.GET)
	public Page<SalarySummary> listBizDepartmentSalaries(@PathVariable String id,
														 @PathVariable int year,
														 @PathVariable int month,
														 PageQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return bizDepartmentMonitorRestSupport.listBizDepartmentSalaries(id, year, month, queryParameter, user);
	}

}
