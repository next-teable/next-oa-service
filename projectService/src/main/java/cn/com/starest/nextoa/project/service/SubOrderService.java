package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.domain.model.SubOrderHistory;
import cn.com.starest.nextoa.project.domain.request.SaveSubOrderRequest;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 订单有关服务
 *
 * @author dz
 */
public interface SubOrderService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param subOrder
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(SubOrder subOrder, User byWho);

	SubOrder createSubOrder(SaveSubOrderRequest request, User byWho);

	SubOrder updateSubOrder(String subOrderId, SaveSubOrderRequest request, User byWho);

	SubOrder publishSubOrder(String subOrderId, User byWho);

	SubOrder unpublishSubOrder(String subOrderId, User byWho);

	void deleteSubOrderById(String subOrderId, User byWho);

	SubOrder findSubOrderById(String subOrderId, User byWho);

	Page<SubOrder> listSubOrders(SubOrderQueryRequest request, User byWho);

	List<SubOrder> listSubOrdersByProject(String projectId, User byWho);

	List<SubOrder> listSubOrdersByProject(Project project, User byWho);

	Page<SubOrderHistory> listSubOrderHistories(String id, PageQueryParameter request);

	SubOrderHistory findSubOrderHistoryById(String id);

}
