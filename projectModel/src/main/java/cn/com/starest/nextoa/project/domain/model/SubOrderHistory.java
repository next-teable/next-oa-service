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
public class SubOrderHistory extends AbstractSubOrder {

	@Indexed
	@DBRef(lazy = true)
	private SubOrder subOrder;

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}
}
