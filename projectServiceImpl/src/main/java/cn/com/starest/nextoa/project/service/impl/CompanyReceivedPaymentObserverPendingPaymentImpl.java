package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.rule.CompanyReceivedPaymentObserver;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class CompanyReceivedPaymentObserverPendingPaymentImpl implements CompanyReceivedPaymentObserver {

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public void onChange(CompanyReceivedPayment companyReceivedPayment) {
		List<PendingPayment> pendingPaymentList = pendingPaymentRepository.findListByCompanyReceivedPaymentOrderByCreatedAtDesc(
				companyReceivedPayment);

		pendingPaymentList.stream().forEach(item -> {
			item.setCompany(companyReceivedPayment.getCompany());
			item.setPendingAt(companyReceivedPayment.getReceivedAt());
			pendingPaymentRepository.save(item);
		});
	}

}
