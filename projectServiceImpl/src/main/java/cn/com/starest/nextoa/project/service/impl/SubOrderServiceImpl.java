package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.SaveSubOrderRequest;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import cn.com.starest.nextoa.project.domain.rule.SubOrderReference;
import cn.com.starest.nextoa.project.exception.*;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.SubOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class SubOrderServiceImpl implements SubOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private SubOrderHistoryRepository subOrderHistoryRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired(required = false)
	private List<SubOrderReference> subOrderReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.SUBORDER, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(SubOrder order, User byWho) {
		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.SUBORDER,
																									 byWho);
		if (order.isPublished()) {
			grantedActions.remove(ModuleActions.DELETE_ACTION);
			grantedActions.remove(ModuleActions.PUBLISH_ACTION);
		}
		else {
			grantedActions.remove(ModuleActions.UNPUBLISH_ACTION);
		}
		return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public SubOrder createSubOrder(SaveSubOrderRequest request, User byWho) {
		checkDuplicateSubOrder(request, null);

		SubOrder subOrder = new SubOrder();
		BeanUtils.copyProperties(request, subOrder);
		handleSubOrderReference(request, subOrder);
		SubOrder.onCreate(subOrder, byWho);

		return subOrderRepository.save(subOrder);
	}

	@Override
	public SubOrder updateSubOrder(String orderId, SaveSubOrderRequest request, User byWho) {
		SubOrder subOrder = subOrderRepository.findById(orderId);
		if (subOrder == null) {
			throw new SubOrderNotFoundException("没有找到对应的分包订单");
		}

		checkDuplicateSubOrder(request, subOrder);

		if (!subOrder.isPublished()) {
			//草稿状态随便改
			return doUpdateSubOrder(subOrder, request, byWho);
		}
		else if (subOrder.isPublished()) {
			//生效后每次修改要记录历史
			return doUpdateSubOrderWithHistory(subOrder, request, byWho);
		}
		else {
			throw new SubOrderException("该分包订单已关闭,不能修改");
		}
	}

	private SubOrder doUpdateSubOrder(SubOrder subOrder, SaveSubOrderRequest request, User byWho) {
		BeanUtils.copyProperties(request, subOrder);
		handleSubOrderReference(request, subOrder);
		SubOrder.onModify(subOrder, byWho);

		return subOrderRepository.save(subOrder);
	}

	public SubOrder doUpdateSubOrderWithHistory(SubOrder subOrder, SaveSubOrderRequest request, User byWho) {
		BeanUtils.copyProperties(request, subOrder);
		handleSubOrderReference(request, subOrder);
		SubOrder.onModify(subOrder, byWho);

		SubOrderHistory subOrderHistory = new SubOrderHistory();
		BeanUtils.copyProperties(subOrder, subOrderHistory, "id");
		subOrderHistory.setSubOrder(subOrder);
		SubOrderHistory.onModify(subOrderHistory, byWho);

		subOrderHistoryRepository.save(subOrderHistory);
		return subOrderRepository.save(subOrder);
	}

	@Override
	public SubOrder publishSubOrder(String orderId, User byWho) {
		SubOrder existedSubOrder = subOrderRepository.findById(orderId);
		if (existedSubOrder == null) {
			throw new SubOrderNotFoundException("没有找到对应的分包订单");
		}

		if (existedSubOrder.isPublished()) {
			throw new SubOrderException("该分包订单已生效,请勿重复操作");
		}

		existedSubOrder.setPublished(true);
		existedSubOrder.setPublishAt(new Date());
		existedSubOrder.setPublishBy(byWho);

		SubOrderHistory subOrderHistory = new SubOrderHistory();
		BeanUtils.copyProperties(existedSubOrder, subOrderHistory, "id");
		subOrderHistory.setSubOrder(existedSubOrder);
		SubOrderHistory.onModify(subOrderHistory, byWho);
		subOrderHistoryRepository.save(subOrderHistory);

		return subOrderRepository.save(existedSubOrder);
	}

	@Override
	public SubOrder unpublishSubOrder(String orderId, User byWho) {
		SubOrder existedSubOrder = subOrderRepository.findById(orderId);
		if (existedSubOrder == null) {
			throw new SubOrderNotFoundException("没有找到对应的分包订单");
		}

		if (!existedSubOrder.isPublished()) {
			return existedSubOrder;
		}

		existedSubOrder.setPublished(false);
		existedSubOrder.setPublishAt(null);
		existedSubOrder.setPublishBy(null);

		return subOrderRepository.save(existedSubOrder);
	}

	@Override
	public void deleteSubOrderById(String orderId, User byWho) {
		SubOrder subOrder = subOrderRepository.findById(orderId);
		if (subOrder == null) {
			throw new SubOrderNotFoundException("没有找到对应的分包订单");
		}

		if (subOrder.isPublished()) {
			throw new SubOrderException("该分包订单已生效,不能删除");
		}

		if (subOrderReferenceList != null) {
			subOrderReferenceList.forEach(ref -> {
				if (ref.hasReference(subOrder)) {
					throw new SubOrderException("该分包订单已被其他业务数据引用,不能删除");
				}
			});
		}

		subOrderHistoryRepository.deleteBySubOrder(subOrder);
		subOrderRepository.delete(subOrder);
	}

	@Override
	public SubOrder findSubOrderById(String orderId, User byWho) {
		return subOrderRepository.findById(orderId);
	}

	@Override
	public Page<SubOrder> listSubOrders(SubOrderQueryRequest request, User byWho) {
		return subOrderRepository.queryPage(request);
	}

	@Override
	public List<SubOrder> listSubOrdersByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		if (project == null) {
			throw new ProjectNotFoundException("没有找到对应的项目");
		}

		return subOrderRepository.findListByProject(project);
	}

	@Override
	public List<SubOrder> listSubOrdersByProject(Project project, User byWho) {
		return subOrderRepository.findListByProject(project);
	}

	@Override
	public Page<SubOrderHistory> listSubOrderHistories(String orderId, PageQueryParameter request) {
		SubOrder subOrder = subOrderRepository.findById(orderId);
		if (subOrder == null) {
			throw new SubOrderNotFoundException("没有找到对应的分包订单");
		}

		return subOrderHistoryRepository.findPageBySubOrder(subOrder,
															new PageRequest(request.getStart(),
																			request.getLimit(),
																			new Sort(Sort.Direction.DESC,
																					 "modifiedAt")));
	}

	@Override
	public SubOrderHistory findSubOrderHistoryById(String id) {
		return subOrderHistoryRepository.findById(id);
	}

	private void handleSubOrderReference(SaveSubOrderRequest request, SubOrder subOrder) {
		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new ProjectNotFoundException("无效的项目id");
		}

		Order order = orderRepository.findById(request.getOrderId());
		if (order == null) {
			throw new SubContractNotFoundException("无效的主订单id");
		}

		if (subOrder.getOrder() != null &&
			subOrder.getOrder().isSettled() &&
			!subOrder.getOrder().getId().equals(request.getOrderId())) {
			throw new OrderException("分包订单对应的订单已经结算,不能修改为其他订单");
		}
		else if (order.isSettled()) {
			throw new OrderException("不能选择已经结算的订单");
		}

		SubContract contract = subContractRepository.findById(request.getSubContractId());
		if (contract == null) {
			throw new SubContractNotFoundException("无效的分包合同id");
		}

		subOrder.setProject(project);
		subOrder.setOrder(order);
		subOrder.setSubContract(contract);
	}

	private void checkDuplicateSubOrder(SaveSubOrderRequest request, SubOrder existedSubOrder) {
		if (existedSubOrder == null) {
			if (null != subOrderRepository.findFirstByName(request.getName())) {
				throw new SubOrderException("分包订单名称不能重复");
			}
			if (null != subOrderRepository.findFirstByCode(request.getCode())) {
				throw new SubOrderException("分包订单编码不能重复");
			}
		}
		else {
			SubOrder matchedSubOrder = subOrderRepository.findFirstByName(request.getName());
			if (null != matchedSubOrder && !matchedSubOrder.getId().equals(existedSubOrder.getId())) {
				throw new SubOrderException("分包订单名称不能重复");
			}

			matchedSubOrder = subOrderRepository.findFirstByCode(request.getCode());
			if (null != matchedSubOrder && !matchedSubOrder.getId().equals(existedSubOrder.getId())) {
				throw new SubOrderException("分包订单编码不能重复");
			}
		}
	}


}
