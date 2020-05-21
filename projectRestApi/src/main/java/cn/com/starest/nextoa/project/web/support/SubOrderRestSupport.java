package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.SaveSubOrderRequest;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import cn.com.starest.nextoa.project.web.dto.SubOrderDetail;
import cn.com.starest.nextoa.project.web.dto.SubOrderHistorySummary;
import cn.com.starest.nextoa.project.web.dto.SubOrderSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface SubOrderRestSupport {

	SubOrderSummary createSubOrder(SaveSubOrderRequest request, User byWho);

	SubOrderSummary updateSubOrder(String id, SaveSubOrderRequest request, User byWho);

	void publishSubOrder(String id, User byWho);

	void unpublishSubOrder(String id, User user);

	SubOrderDetail findSubOrderById(String id, User byWho);

	Page<SubOrderSummary> listSubOrders(SubOrderQueryRequest request, User byWho);

	void deleteSubOrderById(String id, User user);

	Page<SubOrderHistorySummary> listSubOrderHistories(String id, PageQueryParameter request, User user);

	SubOrderHistorySummary findSubOrderHistoryById(String id, User user);

}
