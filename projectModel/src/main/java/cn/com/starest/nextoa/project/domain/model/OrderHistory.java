package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 订单修改历史记录
 *
 * @author dz
 */
@Document(collection = "OrderHistories")
public class OrderHistory extends AbstractOrder {

	@Indexed
	@DBRef(lazy = true)
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
