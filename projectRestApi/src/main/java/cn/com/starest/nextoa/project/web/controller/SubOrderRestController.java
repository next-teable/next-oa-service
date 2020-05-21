package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.SubOrderRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("分包订单管理")
@RestController
@RequestMapping("/api")
public class SubOrderRestController {

	@Autowired
	private SubOrderRestSupport subOrderRestSupport;

	@ApiOperation(value = "创建分包订单")
	@RequestMapping(value = "/subOrders", method = RequestMethod.POST)
	public SubOrderSummary createSubOrder(@Validated @RequestBody SaveSubOrderParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.createSubOrder(request, user);
	}

	@ApiOperation(value = "修改分包订单-只能修改未发布的分包订单")
	@RequestMapping(value = "/subOrders/{id}", method = RequestMethod.POST)
	public SubOrderSummary updateSubOrder(@PathVariable String id,
										  @Validated @RequestBody SaveSubOrderParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.updateSubOrder(id, request, user);
	}

	@ApiOperation(value = "发布分包订单-发布后的分包订单才生效")
	@RequestMapping(value = "/subOrders/{id}/publish", method = RequestMethod.POST)
	public void publishSubOrder(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subOrderRestSupport.publishSubOrder(id, user);
	}

	@ApiOperation(value = "发布分包订单-发布后的分包订单才生效")
	@RequestMapping(value = "/subOrders/{id}/unpublish", method = RequestMethod.POST)
	public void unpublishSubOrder(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subOrderRestSupport.unpublishSubOrder(id, user);
	}

	@ApiOperation(value = "查看分包订单")
	@RequestMapping(value = "/subOrders/{id}", method = RequestMethod.GET)
	public SubOrderDetail findSubOrderById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.findSubOrderById(id, user);
	}

	@ApiOperation(value = "删除分包订单-只能删除未发布的分包订单")
	@RequestMapping(value = "/subOrders/{id}", method = RequestMethod.DELETE)
	public void deleteSubOrderById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subOrderRestSupport.deleteSubOrderById(id, user);
	}

	@ApiOperation(value = "查看分包订单列表")
	@RequestMapping(value = "/subOrders", method = RequestMethod.GET)
	public Page<SubOrderSummary> listSubOrders(SubOrderQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.listSubOrders(request, user);
	}

	@ApiOperation(value = "查看分包订单修改历史详情")
	@RequestMapping(value = "/subOrders/histories/{id}", method = RequestMethod.GET)
	public SubOrderHistorySummary findSubOrderHistoryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.findSubOrderHistoryById(id, user);
	}

	@ApiOperation(value = "查看分包订单修改历史列表")
	@RequestMapping(value = "/subOrders/{id}/history", method = RequestMethod.GET)
	public Page<SubOrderHistorySummary> listSubOrderHistories(@PathVariable String id, PageQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subOrderRestSupport.listSubOrderHistories(id, request, user);
	}

}
