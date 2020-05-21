package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.OrderRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("订单管理")
@RestController
@RequestMapping("/api")
public class OrderRestController {

	@Autowired
	private OrderRestSupport orderRestSupport;

	@ApiOperation(value = "创建订单")
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public OrderSummary createOrder(@Validated @RequestBody SaveOrderParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.createOrder(request, user);
	}

	@ApiOperation(value = "修改订单-只能修改未发布的订单")
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
	public OrderSummary updateOrder(@PathVariable String id, @Validated @RequestBody SaveOrderParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.updateOrder(id, request, user);
	}

	@ApiOperation(value = "发布订单-发布后的订单才生效")
	@RequestMapping(value = "/orders/{id}/publish", method = RequestMethod.POST)
	public void publishOrder(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		orderRestSupport.publishOrder(id, user);
	}

	@ApiOperation(value = "取消发布订单-发布后的订单才生效")
	@RequestMapping(value = "/orders/{id}/unpublish", method = RequestMethod.POST)
	public void unpublishOrder(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		orderRestSupport.unpublishOrder(id, user);
	}

	@ApiOperation(value = "查看订单")
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public OrderDetail findOrderById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.findOrderById(id, user);
	}

	@ApiOperation(value = "删除订单-只能删除未发布的订单")
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
	public void deleteOrderById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		orderRestSupport.deleteOrderById(id, user);
	}

	@ApiOperation(value = "查看订单列表")
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public Page<OrderSummary> listOrders(OrderQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.listOrders(request, user);
	}

	@ApiOperation(value = "查看订单修改历史详情")
	@RequestMapping(value = "/orders/histories/{id}", method = RequestMethod.GET)
	public OrderHistorySummary findOrderHistoryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.findOrderHistoryById(id, user);
	}

	@ApiOperation(value = "查看订单修改历史列表")
	@RequestMapping(value = "/orders/{id}/history", method = RequestMethod.GET)
	public Page<OrderHistorySummary> listOrderHistories(@PathVariable String id, PageQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return orderRestSupport.listOrderHistories(id, request, user);
	}

}
