package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.custom.OrderRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Order repo
 */
public interface OrderRepository extends AbstractRepository<Order>, OrderRepositoryCustom {

	Order findFirstByCode(String code);

	Order findFirstByName(String name);

	List<Order> findListByProject(Project project);

	List<Order> findListByProjectAndYear(Project project, int year);

	List<Order> findListByContract(Contract contract);

	Order findFirstByProject(Project project);

	Order findFirstByContract(Contract contract);

}