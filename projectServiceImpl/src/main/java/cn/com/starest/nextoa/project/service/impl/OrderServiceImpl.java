package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveOrderRequest;
import cn.com.starest.nextoa.project.domain.rule.OrderReference;
import cn.com.starest.nextoa.project.exception.*;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.OrderHistoryRepository;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired(required = false)
    private List<OrderReference> orderReferenceList;

    @Autowired
    private ModulePermissionService modulePermissionService;

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
        return modulePermissionService.listGrantedActions(Module.ORDER, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(Order order, User byWho) {
        List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.ORDER,
                byWho);
        if (order.isPublished()) {
            grantedActions.remove(ModuleActions.DELETE_ACTION);
            grantedActions.remove(ModuleActions.PUBLISH_ACTION);
        } else {
            grantedActions.remove(ModuleActions.UNPUBLISH_ACTION);
        }
        return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public Order createOrder(SaveOrderRequest request, User byWho) {
        checkDuplicateOrder(request, null);

        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        handleOrderReference(request, order);
        Order.onCreate(order, byWho);

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String orderId, SaveOrderRequest request, User byWho) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("没有找到对应的订单");
        }

        if (order.isSettled()) {
            throw new OrderException("该订单已结算,不能修改");
        }

        checkDuplicateOrder(request, order);

        if (!order.isPublished()) {
            //草稿状态随便改
            return doUpdateOrder(order, request, byWho);
        } else if (order.isPublished()) {
            //生效后每次修改要记录历史
            return doUpdateOrderWithHistory(order, request, byWho);
        } else {
            throw new OrderException("该订单已关闭,不能修改");
        }
    }

    private Order doUpdateOrder(Order order, SaveOrderRequest request, User byWho) {
        BeanUtils.copyProperties(request, order);
        handleOrderReference(request, order);
        Order.onModify(order, byWho);

        return orderRepository.save(order);
    }

    public Order doUpdateOrderWithHistory(Order order, SaveOrderRequest request, User byWho) {
        BeanUtils.copyProperties(request, order);
        handleOrderReference(request, order);
        Order.onModify(order, byWho);

        OrderHistory orderHistory = new OrderHistory();
        BeanUtils.copyProperties(order, orderHistory, "id");
        orderHistory.setOrder(order);
        OrderHistory.onModify(orderHistory, byWho);

        orderHistoryRepository.save(orderHistory);
        return orderRepository.save(order);
    }

    @Override
    public Order publishOrder(String orderId, User byWho) {
        Order existedOrder = orderRepository.findById(orderId);
        if (existedOrder == null) {
            throw new OrderNotFoundException("没有找到对应的订单");
        }

        if (existedOrder.isPublished()) {
            throw new OrderException("该订单已生效,请勿重复操作");
        }

        existedOrder.setPublished(true);
        existedOrder.setPublishAt(new Date());
        existedOrder.setPublishBy(byWho);

        OrderHistory orderHistory = new OrderHistory();
        BeanUtils.copyProperties(existedOrder, orderHistory, "id");
        orderHistory.setOrder(existedOrder);
        OrderHistory.onModify(orderHistory, byWho);
        orderHistoryRepository.save(orderHistory);

        return orderRepository.save(existedOrder);
    }

    @Override
    public Order unpublishOrder(String orderId, User byWho) {
        Order existedOrder = orderRepository.findById(orderId);
        if (existedOrder == null) {
            throw new OrderNotFoundException("没有找到对应的订单");
        }

        if (!existedOrder.isPublished()) {
            return existedOrder;
        }

        if (existedOrder.isSettled()) {
            throw new OrderException("该订单已结算,不能取消发布");
        }

        existedOrder.setPublished(false);
        existedOrder.setPublishAt(null);
        existedOrder.setPublishBy(null);

        return orderRepository.save(existedOrder);
    }

    @Override
    public void deleteOrderById(String orderId, User byWho) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("没有找到对应的订单");
        }

        if (order.isPublished()) {
            throw new OrderException("该订单已生效,不能删除");
        }

        if (order.isSettled()) {
            throw new OrderException("该订单已结算,不能删除");
        }

        if (orderReferenceList != null) {
            orderReferenceList.forEach(ref -> {
                if (ref.hasReference(order)) {
                    throw new OrderException("该订单已被其他业务数据引用,不能删除");
                }
            });
        }

        orderHistoryRepository.deleteByOrder(order);
        orderRepository.delete(order);
    }

    @Override
    public Order findOrderById(String orderId, User byWho) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Page<Order> listOrders(OrderQueryRequest request, User byWho) {
        return orderRepository.queryPage(request);
    }

    @Override
    public List<Order> listOrdersByProject(String projectId, User byWho) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        return orderRepository.findListByProject(project);
    }

    @Override
    public List<Order> listOrdersByProject(String projectId, int year, User byWho) {
        Project project = projectRepository.findById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("没有找到对应的项目");
        }

        return orderRepository.findListByProjectAndYear(project, year);
    }

    @Override
    public List<Order> listOrdersByProject(Project project, User byWho) {
        return orderRepository.findListByProject(project);
    }

    @Override
    public List<Order> listOrdersByContract(String contractId, User byWho) {
        Contract contract = contractRepository.findById(contractId);
        if (contract == null) {
            throw new ContractNotFoundException("没有找到对应的合同");
        }

        return orderRepository.findListByContract(contract);
    }

    @Override
    public List<Order> listOrdersByContract(Contract contract, User byWho) {
        return orderRepository.findListByContract(contract);
    }

    @Override
    public Page<OrderHistory> listOrderHistories(String orderId, PageQueryRequest pageQueryRequest) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("没有找到对应的订单");
        }

        return orderHistoryRepository.findPageByOrder(order,
                new PageRequest(pageQueryRequest.getStart(),
                        pageQueryRequest.getLimit(),
                        new Sort(Sort.Direction.DESC, "modifiedAt")));
    }

    @Override
    public OrderHistory findOrderHistoryById(String id) {
        return orderHistoryRepository.findById(id);
    }

    private void handleOrderReference(SaveOrderRequest request, Order order) {
        Project project = projectRepository.findById(request.getProjectId());
        if (project == null) {
            throw new ProjectNotFoundException("无效的项目id");
        }

        Contract contract = contractRepository.findById(request.getContractId());
        if (contract == null) {
            throw new ContractNotFoundException("无效的合同id");
        }

        if (order.getContract() != null &&
                order.getContract().isSettled() &&
                !order.getContract().getId().equals(request.getContractId())) {
            throw new ContractException("该订单对应的合同已经结算,不能修改为其他合同");
        } else if (contract.isSettled()) {
            throw new ContractException("不能选择已经结算的合同");
        }

        order.setProject(project);
        order.setContract(contract);
    }


    private void checkDuplicateOrder(SaveOrderRequest request, Order existedOrder) {
        if (existedOrder == null) {
            if (null != orderRepository.findFirstByName(request.getName())) {
                throw new OrderException("订单名称不能重复");
            }
            if (null != orderRepository.findFirstByCode(request.getCode())) {
                throw new OrderException("订单编码不能重复");
            }
        } else {
            Order matchedOrder = orderRepository.findFirstByName(request.getName());
            if (null != matchedOrder && !matchedOrder.getId().equals(existedOrder.getId())) {
                throw new OrderException("订单名称不能重复");
            }

            matchedOrder = orderRepository.findFirstByCode(request.getCode());
            if (null != matchedOrder && !matchedOrder.getId().equals(existedOrder.getId())) {
                throw new OrderException("订单编码不能重复");
            }
        }
    }


}
