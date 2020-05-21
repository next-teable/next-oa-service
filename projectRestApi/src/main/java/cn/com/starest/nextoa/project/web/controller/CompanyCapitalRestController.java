package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalSummary;
import cn.com.starest.nextoa.project.web.dto.SaveCompanyCapitalParameter;
import cn.com.starest.nextoa.project.web.support.CompanyCapitalRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("公司资金")
@RestController
@RequestMapping("/api")
public class CompanyCapitalRestController {

	@Autowired
	private CompanyCapitalRestSupport companyCapitalRestSupport;

	@ApiOperation(value = "创建公司资金")
	@RequestMapping(value = "/companyCapitals", method = RequestMethod.POST)
	public CompanyCapitalSummary createCompanyCapital(@Validated @RequestBody SaveCompanyCapitalParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyCapitalRestSupport.createCompanyCapital(request, user);
	}

	@ApiOperation(value = "修改公司资金")
	@RequestMapping(value = "/companyCapitals/{id}", method = RequestMethod.POST)
	public CompanyCapitalSummary updateCompanyCapital(@PathVariable String id,
													  @Validated @RequestBody SaveCompanyCapitalParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyCapitalRestSupport.updateCompanyCapital(id, request, user);
	}

	@ApiOperation(value = "查看公司资金")
	@RequestMapping(value = "/companyCapitals/{id}", method = RequestMethod.GET)
	public CompanyCapitalDetail findCompanyCapitalById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return companyCapitalRestSupport.findCompanyCapitalById(id, user);
	}

	@ApiOperation(value = "删除公司资金")
	@RequestMapping(value = "/companyCapitals/{id}", method = RequestMethod.DELETE)
	public void deleteCompanyCapitalById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		companyCapitalRestSupport.deleteCompanyCapitalById(id, user);
	}

	@ApiOperation(value = "查看公司资金列表")
	@RequestMapping(value = "/companyCapitals", method = RequestMethod.GET)
	public Page<CompanyCapitalSummary> listCompanyCapitals(CompanyCapitalQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyCapitalRestSupport.listCompanyCapitals(request, user);
	}

}
