package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectCompletionByOrderListener implements EventListener<Order>, InitializingBean {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void onEvent(Order order) {
		if (order == null) {
			return;
		}
		if (order.getProject() == null) {
			return;
		}

		if (!order.isCompleted()) {
			order.getProject().setStatus(ProjectStatus.RUNNING);
			projectRepository.save(order.getProject());
			return;
		}

		if (orderRepository.findListByProject(order.getProject())
						   .stream()
						   .filter(item -> !item.isCompleted())
						   .count() == 0) {
			order.getProject().setStatus(ProjectStatus.COMPLETED);
			projectRepository.save(order.getProject());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("project").register("completeByOrder", this);
	}

}
