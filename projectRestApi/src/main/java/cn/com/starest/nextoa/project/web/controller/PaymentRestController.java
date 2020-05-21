package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.PaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SavePaymentParameter;
import cn.com.starest.nextoa.project.web.support.PaymentRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("付款管理")
@RestController
@RequestMapping("/api")
public class PaymentRestController {

	@Autowired
	private PaymentRestSupport paymentRestSupport;

	@ApiOperation(value = "创建付款")
	@RequestMapping(value = "/payments", method = RequestMethod.POST)
	public PaymentSummary createPayment(@Validated @RequestBody SavePaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return paymentRestSupport.createPayment(request, user);
	}

	@ApiOperation(value = "修改付款")
	@RequestMapping(value = "/payments/{id}", method = RequestMethod.POST)
	public PaymentSummary updatePayment(@PathVariable String id, @Validated @RequestBody SavePaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return paymentRestSupport.updatePayment(id, request, user);
	}

	@ApiOperation(value = "查看付款")
	@RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
	public PaymentDetail findPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return paymentRestSupport.findPaymentById(id, user);
	}

	@ApiOperation(value = "删除付款(如果是通过快文产生的,不能删除)")
	@RequestMapping(value = "/payments/{id}", method = RequestMethod.DELETE)
	public void deletePaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paymentRestSupport.deletePaymentById(id, user);
	}

	@ApiOperation(value = "查看付款列表")
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public Page<PaymentSummary> listPayments(PaymentQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return paymentRestSupport.listPayments(request, user);
	}

}
