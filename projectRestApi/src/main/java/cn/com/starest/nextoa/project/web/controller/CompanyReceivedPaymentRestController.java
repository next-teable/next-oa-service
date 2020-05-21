package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SaveCompanyReceivedPaymentParameter;
import cn.com.starest.nextoa.project.web.support.CompanyReceivedPaymentRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("公司回款")
@RestController
@RequestMapping("/api")
public class CompanyReceivedPaymentRestController {

	@Autowired
	private CompanyReceivedPaymentRestSupport companyReceivedPaymentRestSupport;

	@ApiOperation(value = "创建公司回款")
	@RequestMapping(value = "/companyReceivedPayments", method = RequestMethod.POST)
	public CompanyReceivedPaymentSummary createCompanyReceivedPayment(@Validated @RequestBody SaveCompanyReceivedPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyReceivedPaymentRestSupport.createCompanyReceivedPayment(request, user);
	}

	@ApiOperation(value = "修改公司回款")
	@RequestMapping(value = "/companyReceivedPayments/{id}", method = RequestMethod.POST)
	public CompanyReceivedPaymentSummary updateCompanyReceivedPayment(@PathVariable String id,
																	  @Validated @RequestBody SaveCompanyReceivedPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyReceivedPaymentRestSupport.updateCompanyReceivedPayment(id, request, user);
	}

	@ApiOperation(value = "查看公司回款")
	@RequestMapping(value = "/companyReceivedPayments/{id}", method = RequestMethod.GET)
	public CompanyReceivedPaymentDetail findCompanyReceivedPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return companyReceivedPaymentRestSupport.findCompanyReceivedPaymentById(id, user);
	}

	@ApiOperation(value = "删除公司回款")
	@RequestMapping(value = "/companyReceivedPayments/{id}", method = RequestMethod.DELETE)
	public void deleteCompanyReceivedPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		companyReceivedPaymentRestSupport.deleteCompanyReceivedPaymentById(id, user);
	}

	@ApiOperation(value = "查看公司回款列表")
	@RequestMapping(value = "/companyReceivedPayments", method = RequestMethod.GET)
	public Page<CompanyReceivedPaymentSummary> listCompanyReceivedPayments(CompanyReceivedPaymentQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return companyReceivedPaymentRestSupport.listCompanyReceivedPayments(request, user);
	}

}
