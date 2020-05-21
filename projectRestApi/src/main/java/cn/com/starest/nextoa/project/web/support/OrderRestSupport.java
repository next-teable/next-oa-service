package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveOrderRequest;
import cn.com.starest.nextoa.project.web.dto.OrderDetail;
import cn.com.starest.nextoa.project.web.dto.OrderHistorySummary;
import cn.com.starest.nextoa.project.web.dto.OrderSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface OrderRestSupport {

	OrderSummary createOrder(SaveOrderRequest request, User byWho);

	OrderSummary updateOrder(String id, SaveOrderRequest request, User byWho);

	void publishOrder(String id, User byWho);

	void unpublishOrder(String id, User user);

	OrderDetail findOrderById(String id, User byWho);

	Page<OrderSummary> listOrders(OrderQueryRequest request, User byWho);

	void deleteOrderById(String id, User user);

	Page<OrderHistorySummary> listOrderHistories(String id, PageQueryParameter request, User user);

	OrderHistorySummary findOrderHistoryById(String id, User user);

}
