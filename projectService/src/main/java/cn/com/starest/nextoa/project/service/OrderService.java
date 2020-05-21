package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveOrderRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 订单有关服务
 *
 * @author dz
 */
public interface OrderService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param order
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(Order order, User byWho);

	Order createOrder(SaveOrderRequest request, User byWho);

	Order updateOrder(String orderId, SaveOrderRequest request, User byWho);

	Order publishOrder(String orderId, User byWho);

	Order unpublishOrder(String id, User byWho);

	void deleteOrderById(String orderId, User byWho);

	Order findOrderById(String orderId, User byWho);

	Page<Order> listOrders(OrderQueryRequest request, User byWho);

	List<Order> listOrdersByProject(String projectId, User byWho);

	List<Order> listOrdersByProject(String projectId, int year, User byWho);

	List<Order> listOrdersByProject(Project project, User byWho);

	List<Order> listOrdersByContract(String contractId, User byWho);

	List<Order> listOrdersByContract(Contract contract, User byWho);

	Page<OrderHistory> listOrderHistories(String id, PageQueryRequest request);

	OrderHistory findOrderHistoryById(String id);

}
