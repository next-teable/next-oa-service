package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.repository.custom.SubOrderRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface SubOrderRepository extends AbstractRepository<SubOrder>, SubOrderRepositoryCustom {

	SubOrder findFirstByCode(String code);

	SubOrder findFirstByName(String name);

	List<SubOrder> findListByProject(Project project);

	List<SubOrder> findListByOrder(Order order);

	List<SubOrder> findListBySubContract(SubContract contract);

	SubOrder findFirstByProject(Project project);

	SubOrder findFirstByOrder(Order order);

	SubOrder findFirstBySubContract(SubContract contract);

}