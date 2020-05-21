package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.domain.model.SubOrderHistory;
import cn.com.starest.nextoa.project.domain.request.SaveSubOrderRequest;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import cn.com.starest.nextoa.project.service.SubOrderService;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.SubOrderDetail;
import cn.com.starest.nextoa.project.web.dto.SubOrderHistorySummary;
import cn.com.starest.nextoa.project.web.dto.SubOrderSummary;
import cn.com.starest.nextoa.project.web.support.SubOrderRestSupport;
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
public class SubOrderRestSupportImpl implements SubOrderRestSupport {

	@Autowired
	private SubOrderService subOrderService;

	@Override
	public SubOrderSummary createSubOrder(SaveSubOrderRequest request, User byWho) {
		SubOrder subOrder = subOrderService.createSubOrder(request, byWho);
		SubOrderSummary result = SubOrderSummary.from(subOrder);
		result.setGrantedActions(subOrderService.resolveGrantedActions(subOrder, byWho));
		return result;
	}

	@Override
	public SubOrderSummary updateSubOrder(String id, SaveSubOrderRequest request, User byWho) {
		SubOrder subOrder = subOrderService.updateSubOrder(id, request, byWho);
		SubOrderSummary result = SubOrderSummary.from(subOrder);
		result.setGrantedActions(subOrderService.resolveGrantedActions(subOrder, byWho));
		return result;
	}

	@Override
	public void publishSubOrder(String id, User byWho) {
		subOrderService.publishSubOrder(id, byWho);
	}

	@Override
	public void unpublishSubOrder(String id, User byWho) {
		subOrderService.unpublishSubOrder(id, byWho);
	}

	@Override
	public SubOrderDetail findSubOrderById(String id, User byWho) {
		SubOrder subOrder = subOrderService.findSubOrderById(id, byWho);
		SubOrderDetail result = SubOrderDetail.from(subOrder);
		result.setGrantedActions(subOrderService.resolveGrantedActions(subOrder, byWho));
		return result;
	}

	@Override
	public Page<SubOrderSummary> listSubOrders(SubOrderQueryRequest request, User byWho) {
		Page<SubOrder> subOrderPage = subOrderService.listSubOrders(request, byWho);
		return new PermissionAwarePageImpl<>(subOrderPage.getContent().stream().map(item -> {
			SubOrderSummary subOrderSummary = SubOrderSummary.from(item);
			subOrderSummary.setGrantedActions(subOrderService.resolveGrantedActions(item, byWho));
			return subOrderSummary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 subOrderPage.getTotalElements(),
											 subOrderService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteSubOrderById(String id, User user) {
		subOrderService.deleteSubOrderById(id, user);
	}

	@Override
	public Page<SubOrderHistorySummary> listSubOrderHistories(String id, PageQueryParameter request, User user) {
		Page<SubOrderHistory> subOrderHistoryPage = subOrderService.listSubOrderHistories(id, request);
		return new PageImpl<>(subOrderHistoryPage.getContent()
												 .stream()
												 .map(SubOrderHistorySummary::from)
												 .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  subOrderHistoryPage.getTotalElements());
	}

	@Override
	public SubOrderHistorySummary findSubOrderHistoryById(String id, User user) {
		return SubOrderHistorySummary.from(subOrderService.findSubOrderHistoryById(id));
	}
}
