package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.OrderHistory;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * OrderHistory repo
 */
public interface OrderHistoryRepository extends AbstractRepository<OrderHistory> {

	Page<OrderHistory> findPageByOrder(Order order, Pageable pageable);

	void deleteByOrder(Order order);

	OrderHistory findFirstByProject(Project project);

	OrderHistory findFirstByContract(Contract contract);

	OrderHistory findFirstByOrder(Order target);
}
