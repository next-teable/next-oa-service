package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.OrderHistory;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveOrderRequest;
import cn.com.starest.nextoa.project.service.OrderService;
import cn.com.starest.nextoa.project.web.dto.OrderDetail;
import cn.com.starest.nextoa.project.web.dto.OrderHistorySummary;
import cn.com.starest.nextoa.project.web.dto.OrderSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.OrderRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class OrderRestSupportImpl implements OrderRestSupport {

	@Autowired
	private OrderService orderService;

	@Override
	public OrderSummary createOrder(SaveOrderRequest request, User byWho) {
		Order order = orderService.createOrder(request, byWho);
		OrderSummary result = OrderSummary.from(order);
		result.setGrantedActions(orderService.resolveGrantedActions(order, byWho));
		return result;
	}

	@Override
	public OrderSummary updateOrder(String id, SaveOrderRequest request, User byWho) {
		Order order = orderService.updateOrder(id, request, byWho);
		OrderSummary result = OrderSummary.from(order);
		result.setGrantedActions(orderService.resolveGrantedActions(order, byWho));
		return result;
	}

	@Override
	public void publishOrder(String id, User byWho) {
		orderService.publishOrder(id, byWho);
	}

	@Override
	public void unpublishOrder(String id, User byWho) {
		orderService.unpublishOrder(id, byWho);
	}

	@Override
	public OrderDetail findOrderById(String id, User byWho) {
		Order order = orderService.findOrderById(id, byWho);
		OrderDetail result = OrderDetail.from(order);
		result.setGrantedActions(orderService.resolveGrantedActions(order, byWho));
		return result;
	}

	@Override
	public Page<OrderSummary> listOrders(OrderQueryRequest request, User byWho) {
		Page<Order> orderPage = orderService.listOrders(request, byWho);
		return new PermissionAwarePageImpl<>(orderPage.getContent().stream().map(item -> {
			OrderSummary orderSummary = OrderSummary.from(item);
			orderSummary.setGrantedActions(orderService.resolveGrantedActions(item, byWho));
			return orderSummary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 orderPage.getTotalElements(),
											 orderService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteOrderById(String id, User user) {
		orderService.deleteOrderById(id, user);
	}


	@Override
	public Page<OrderHistorySummary> listOrderHistories(String id, PageQueryParameter request, User user) {
		Page<OrderHistory> orderHistoryPage = orderService.listOrderHistories(id, request);
		return new PageImpl<>(orderHistoryPage.getContent()
											  .stream()
											  .map(OrderHistorySummary::from)
											  .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  orderHistoryPage.getTotalElements());
	}

	@Override
	public OrderHistorySummary findOrderHistoryById(String id, User user) {
		return OrderHistorySummary.from(orderService.findOrderHistoryById(id));
	}
}
