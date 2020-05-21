package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SavePendingPaymentParameter;
import cn.com.starest.nextoa.project.web.support.PendingPaymentRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("待付款")
@RestController
@RequestMapping("/api")
public class PendingPaymentRestController {

	@Autowired
	private PendingPaymentRestSupport pendingPaymentRestSupport;

	@ApiOperation(value = "创建待付款")
	@RequestMapping(value = "/pendingPayments", method = RequestMethod.POST)
	public PendingPaymentSummary createPendingPayment(@Validated @RequestBody SavePendingPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return pendingPaymentRestSupport.createPendingPayment(request, user);
	}

	@ApiOperation(value = "修改待付款")
	@RequestMapping(value = "/pendingPayments/{id}", method = RequestMethod.POST)
	public PendingPaymentSummary updatePendingPayment(@PathVariable String id,
													  @Validated @RequestBody SavePendingPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return pendingPaymentRestSupport.updatePendingPayment(id, request, user);
	}

	@ApiOperation(value = "查看待付款")
	@RequestMapping(value = "/pendingPayments/{id}", method = RequestMethod.GET)
	public PendingPaymentDetail findPendingPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return pendingPaymentRestSupport.findPendingPaymentById(id, user);
	}

	@ApiOperation(value = "删除待付款")
	@RequestMapping(value = "/pendingPayments/{id}", method = RequestMethod.DELETE)
	public void deletePendingPaymentById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		pendingPaymentRestSupport.deletePendingPaymentById(id, user);
	}

	@ApiOperation(value = "查看待付款列表")
	@RequestMapping(value = "/pendingPayments", method = RequestMethod.GET)
	public Page<PendingPaymentSummary> listPendingPayments(PendingPaymentQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return pendingPaymentRestSupport.listPendingPayments(request, user);
	}

}
