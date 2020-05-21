package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceSummary;
import cn.com.starest.nextoa.project.web.dto.SaveReceiveInvoiceParameter;
import cn.com.starest.nextoa.project.web.support.ReceiveInvoiceRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("收票管理")
@RestController
@RequestMapping("/api")
public class ReceiveInvoiceRestController {

	@Autowired
	private ReceiveInvoiceRestSupport receiveInvoiceRestSupport;

	@ApiOperation(value = "创建票据（收票）")
	@RequestMapping(value = "/receiveInvoices", method = RequestMethod.POST)
	public ReceiveInvoiceSummary createReceiveInvoice(@Validated @RequestBody SaveReceiveInvoiceParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return receiveInvoiceRestSupport.createReceiveInvoice(request, user);
	}

	@ApiOperation(value = "修改票据（收票）")
	@RequestMapping(value = "/receiveInvoices/{id}", method = RequestMethod.POST)
	public ReceiveInvoiceSummary updateReceiveInvoice(@PathVariable String id,
													  @Validated @RequestBody SaveReceiveInvoiceParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return receiveInvoiceRestSupport.updateReceiveInvoice(id, request, user);
	}

	@ApiOperation(value = "查看票据（收票）")
	@RequestMapping(value = "/receiveInvoices/{id}", method = RequestMethod.GET)
	public ReceiveInvoiceDetail findReceiveInvoiceById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return receiveInvoiceRestSupport.findReceiveInvoiceById(id, user);
	}

	@ApiOperation(value = "删除票据（收票）")
	@RequestMapping(value = "/receiveInvoices/{id}", method = RequestMethod.DELETE)
	public void deleteReceiveInvoiceById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		receiveInvoiceRestSupport.deleteReceiveInvoiceById(id, user);
	}

	@ApiOperation(value = "查看票据（收票）列表")
	@RequestMapping(value = "/receiveInvoices", method = RequestMethod.GET)
	public Page<ReceiveInvoiceSummary> listReceiveInvoices(ReceiveInvoiceQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return receiveInvoiceRestSupport.listReceiveInvoices(request, user);
	}

}
