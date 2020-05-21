package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.CompanyCapitalReport;
import cn.com.starest.nextoa.project.domain.model.CompanyFinancialReport;
import cn.com.starest.nextoa.project.web.support.FinancialReportRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("公司财务报表")
@RestController
@RequestMapping("/api/report")
public class CompanyMonitorRestController {

	@Autowired
	private FinancialReportRestSupport financialReportRestSupport;

	@ApiOperation(value = "公司财务报表")
	@RequestMapping(value = "/company/financialReport", method = RequestMethod.GET)
	public List<CompanyFinancialReport> getCompanyFinancialReport() {
		User user = SecurityContexts.getContext().requireUser();
		return financialReportRestSupport.getCompanyFinancialReport(user);
	}

	@ApiOperation(value = "公司财务报表")
	@RequestMapping(value = "/company/{id}/{year}/capitalReport", method = RequestMethod.GET)
	public CompanyCapitalReport getCompanyCapitalReport(@PathVariable String id, @PathVariable int year) {
		User user = SecurityContexts.getContext().requireUser();
		return financialReportRestSupport.getCompanyCapitalReport(id, year, user);
	}

}
