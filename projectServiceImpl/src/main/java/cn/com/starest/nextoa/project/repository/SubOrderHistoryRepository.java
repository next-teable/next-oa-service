package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface SubOrderHistoryRepository extends AbstractRepository<SubOrderHistory> {

	Page<SubOrderHistory> findPageBySubOrder(SubOrder order, Pageable pageable);

	void deleteBySubOrder(SubOrder order);

	SubOrderHistory findFirstByProject(Project project);

	SubOrderHistory findFirstByOrder(Order order);

	SubOrderHistory findFirstBySubContract(SubContract contract);

	SubOrderHistory findFirstBySubOrder(SubOrder target);
}
