package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.request.SaveSalaryRequest;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.SalaryRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

@Api("工资")
@RestController
@RequestMapping("/api")
public class SalaryRestController {

	@Autowired
	private SalaryRestSupport salaryRestSupport;


	/**
	 *
	 * @param queryParameter 高级查询只支持选company或year
	 * @return
	 */
	@ApiOperation(value = "查询工资汇总 - 按公司按年汇总")
	@RequestMapping(value = "/salaries/aggregationByYear", method = RequestMethod.GET)
	public Page<SalaryAggregationSummary> listSalaryAggregationByYear(SalaryAggregationQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.listSalaryAggregationByYear(queryParameter, user);
	}

	/**
	 *
	 * @param queryParameter 必须传companyId,不需要支持高级查询
	 * @return
	 */
	@ApiOperation(value = "查询工资汇总 - 按公司按年按月汇总")
	@RequestMapping(value = "/salaries/aggregationByMonth", method = RequestMethod.GET)
	public Page<SalaryAggregationSummary> listSalaryAggregationByMonth(SalaryAggregationQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.listSalaryAggregationByMonth(queryParameter, user);
	}

//	/**
//	 * @param queryParameter if the month is null or illegal , it means calculate aggregation by year
//	 * @return
//	 */
//	@ApiOperation(value = "查询工资汇总")
//	@RequestMapping(value = "/salaries/byAggregation", method = RequestMethod.GET)
//	public Page<SalaryAggregationSummary> listSalaryByAggregation(SalaryAggregationQueryParameter queryParameter) {
//		User user = SecurityContexts.getContext().requireUser();
//		return salaryRestSupport.listSalaryByAggregation(queryParameter, user);
//	}

	@ApiOperation(value = "导入工资")
	@RequestMapping(value = "/salaries/import", method = RequestMethod.POST)
	public SalaryImportHistorySummary importSalary(MultipartRequest multipartRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.importSalary(multipartRequest, user);
	}

	@ApiOperation(value = "查询导入记录")
	@RequestMapping(value = "/salaries/import", method = RequestMethod.GET)
	public Page<SalaryImportHistorySummary> listSalaryImportHistories(PageQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.listSalaryImportHistories(queryParameter, user);
	}

	@ApiOperation(value = "创建工资")
	@RequestMapping(value = "/salaries", method = RequestMethod.POST)
	public SalarySummary createSalary(@Validated @RequestBody SaveSalaryRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.createSalary(request, user);
	}

	@ApiOperation(value = "修改工资")
	@RequestMapping(value = "/salaries/{id}", method = RequestMethod.POST)
	public SalarySummary updateSalary(@PathVariable String id, @Validated @RequestBody SaveSalaryRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.updateSalary(id, request, user);
	}

	@ApiOperation(value = "查看工资")
	@RequestMapping(value = "/salaries/{id}", method = RequestMethod.GET)
	public SalaryDetail findSalaryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.findSalaryById(id, user);
	}

	@ApiOperation(value = "删除工资")
	@RequestMapping(value = "/salaries/{id}", method = RequestMethod.DELETE)
	public void deleteSalaryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		salaryRestSupport.deleteSalaryById(id, user);
	}

	@ApiOperation(value = "查看工资列表")
	@RequestMapping(value = "/salaries", method = RequestMethod.GET)
	public Page<SalarySummary> listSalaries(SalaryQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return salaryRestSupport.listSalaries(request, user);
	}

}
