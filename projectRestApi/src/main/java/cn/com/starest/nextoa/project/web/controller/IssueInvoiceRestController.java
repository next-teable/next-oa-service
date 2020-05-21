package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceSummary;
import cn.com.starest.nextoa.project.web.dto.SaveIssueInvoiceParameter;
import cn.com.starest.nextoa.project.web.support.IssueInvoiceRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("开票管理")
@RestController
@RequestMapping("/api")
public class IssueInvoiceRestController {

	@Autowired
	private IssueInvoiceRestSupport issueInvoiceRestSupport;

	@ApiOperation(value = "创建票据（开票）")
	@RequestMapping(value = "/issueInvoices", method = RequestMethod.POST)
	public IssueInvoiceSummary createIssueInvoice(@Validated @RequestBody SaveIssueInvoiceParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return issueInvoiceRestSupport.createIssueInvoice(request, user);
	}

	@ApiOperation(value = "修改票据（开票）")
	@RequestMapping(value = "/issueInvoices/{id}", method = RequestMethod.POST)
	public IssueInvoiceSummary updateIssueInvoice(@PathVariable String id,
												  @Validated @RequestBody SaveIssueInvoiceParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return issueInvoiceRestSupport.updateIssueInvoice(id, request, user);
	}

	@ApiOperation(value = "查看票据（开票）")
	@RequestMapping(value = "/issueInvoices/{id}", method = RequestMethod.GET)
	public IssueInvoiceDetail findIssueInvoiceById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return issueInvoiceRestSupport.findIssueInvoiceById(id, user);
	}

	@ApiOperation(value = "删除票据（开票）")
	@RequestMapping(value = "/issueInvoices/{id}", method = RequestMethod.DELETE)
	public void deleteIssueInvoiceById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		issueInvoiceRestSupport.deleteIssueInvoiceById(id, user);
	}

	@ApiOperation(value = "查看票据（开票）列表")
	@RequestMapping(value = "/issueInvoices", method = RequestMethod.GET)
	public Page<IssueInvoiceSummary> listIssueInvoices(IssueInvoiceQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return issueInvoiceRestSupport.listIssueInvoices(request, user);
	}

}
