package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.rule.ProjectReceivedPaymentObserver;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class ProjectReceivedPaymentObserverPendingPaymentImpl implements ProjectReceivedPaymentObserver {

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public void onChange(ProjectReceivedPayment projectReceivedPayment) {
		List<PendingPayment> pendingPaymentList = pendingPaymentRepository.findListByProjectReceivedPaymentOrderByCreatedAtDesc(
				projectReceivedPayment);

		pendingPaymentList.stream().forEach(item -> {
			item.setCompany(projectReceivedPayment.getCompany());
			item.setProject(projectReceivedPayment.getProject());
			item.setPendingAt(projectReceivedPayment.getReceivedAt());
			pendingPaymentRepository.save(item);
		});
	}

}
