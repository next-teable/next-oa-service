package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.LendingRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("借还款管理")
@RestController
@RequestMapping("/api")
public class LendingRestController {

	@Autowired
	private LendingRestSupport lendingRestSupport;

	@ApiOperation(value = "用户模块权限")
	@RequestMapping(value = "/lendings/grantedActions", method = RequestMethod.GET)
	public ModuleActions.ModuleAction[] resolveGrantedActions() {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.resolveGrantedActions(user);
	}

	@ApiOperation(value = "借还款汇总-公司")
	@RequestMapping(value = "/lendings/aggregationBySubContractor", method = RequestMethod.GET)
	public Page<LendingAggregationSummary> listLendingAggregationBySubContractor(LendingAggregationQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.listLendingAggregationBySubContractor(request, user);
	}

	@ApiOperation(value = "借还款-个人")
	@RequestMapping(value = "/lendings/aggregationByUser", method = RequestMethod.GET)
	public Page<LendingAggregationSummary> listLendingAggregationByUser(LendingAggregationQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.listLendingAggregationByUser(request, user);
	}

	@ApiOperation(value = "借还款列表-公司")
	@RequestMapping(value = "/lendings/bySubContractor/{id}", method = RequestMethod.GET)
	public Page<LendingSummary> listLendingBySubContractor(@PathVariable String id, LendingQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.listLendingBySubContractor(id, request, user);
	}

	@ApiOperation(value = "借还款列表-个人")
	@RequestMapping(value = "/lendings/byUser/{id}", method = RequestMethod.GET)
	public Page<LendingSummary> listLendingByUser(@PathVariable String id, LendingQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.listLendingByUser(id, request, user);
	}

	@ApiOperation(value = "创建借还款申请")
	@RequestMapping(value = "/lendings", method = RequestMethod.POST)
	public LendingSummary createLending(@Validated @RequestBody SaveLendingParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.createLending(request, user);
	}

	@ApiOperation(value = "修改借还款申请")
	@RequestMapping(value = "/lendings/{id}", method = RequestMethod.POST)
	public LendingSummary updateLending(@PathVariable String id, @Validated @RequestBody SaveLendingParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.updateLending(id, request, user);
	}

	@ApiOperation(value = "查看借还款申请")
	@RequestMapping(value = "/lendings/{id}", method = RequestMethod.GET)
	public LendingDetail findLendingById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.findLendingById(id, user);
	}

	@ApiOperation(value = "删除借还款申请-只能删除未结算的借还款申请")
	@RequestMapping(value = "/lendings/{id}", method = RequestMethod.DELETE)
	public void deleteLendingById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		lendingRestSupport.deleteLendingById(id, user);
	}

	@ApiOperation(value = "查看借还款申请列表")
	@RequestMapping(value = "/lendings", method = RequestMethod.GET)
	public Page<LendingSummary> listLendings(LendingQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return lendingRestSupport.listLendings(request, user);
	}


}
