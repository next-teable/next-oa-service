package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.DepositDetail;
import cn.com.starest.nextoa.project.web.dto.DepositQueryParameter;
import cn.com.starest.nextoa.project.web.dto.DepositSummary;
import cn.com.starest.nextoa.project.web.dto.SaveDepositParameter;
import cn.com.starest.nextoa.project.web.support.DepositRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("保证金")
@RestController
@RequestMapping("/api")
public class DepositRestController {

	@Autowired
	private DepositRestSupport depositRestSupport;

	@ApiOperation(value = "创建保证金")
	@RequestMapping(value = "/deposits", method = RequestMethod.POST)
	public DepositSummary createDeposit(@Validated @RequestBody SaveDepositParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return depositRestSupport.createDeposit(request, user);
	}

	@ApiOperation(value = "修改保证金")
	@RequestMapping(value = "/deposits/{id}", method = RequestMethod.POST)
	public DepositSummary updateDeposit(@PathVariable String id, @Validated @RequestBody SaveDepositParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return depositRestSupport.updateDeposit(id, request, user);
	}

	@ApiOperation(value = "查看保证金")
	@RequestMapping(value = "/deposits/{id}", method = RequestMethod.GET)
	public DepositDetail findDepositById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return depositRestSupport.findDepositById(id, user);
	}

	@ApiOperation(value = "删除保证金")
	@RequestMapping(value = "/deposits/{id}", method = RequestMethod.DELETE)
	public void deleteDepositById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		depositRestSupport.deleteDepositById(id, user);
	}

	@ApiOperation(value = "查看保证金列表")
	@RequestMapping(value = "/deposits", method = RequestMethod.GET)
	public Page<DepositSummary> listDeposits(DepositQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return depositRestSupport.listDeposits(request, user);
	}

}
