package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.PaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SavePaymentParameter;
import cn.com.starest.nextoa.project.web.support.ReimburseDevOpsRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("付款（来自报销）管理")
@RestController
@RequestMapping("/api")
public class ReimburseDevOpsRestController {

	@Autowired
	private ReimburseDevOpsRestSupport reimburseRestSupport;

	@ApiOperation(value = "修改付款（来自报销）")
	@RequestMapping(value = "/reimburseDevOps/{id}", method = RequestMethod.POST)
	public PaymentSummary updatePayment(@PathVariable String id, @Validated @RequestBody SavePaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.updatePayment(id, request, user);
	}

	@ApiOperation(value = "查看付款（来自报销）")
	@RequestMapping(value = "/reimburseDevOps/{id}", method = RequestMethod.GET)
	public PaymentDetail findPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.findPaymentById(id, user);
	}

	@ApiOperation(value = "查看付款（来自报销）列表")
	@RequestMapping(value = "/reimburseDevOps", method = RequestMethod.GET)
	public Page<PaymentSummary> listPayments(PaymentQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.listPayments(request, user);
	}

}
