package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface OrderRepositoryCustom {

	BigDecimal calculateTotalAmount(Project project);

	BigDecimal calculateTotalAmount(Project project, int year);

	Page<Order> queryPage(OrderQueryRequest request);

}
