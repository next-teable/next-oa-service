package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.rule.ReimburseReference;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ReimburseReferenceImpl implements ReimburseReference {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public boolean hasReference(Reimburse target) {
		if (target == null) {
			return false;
		}
		return paymentRepository.findFirstBySourceId(target.getId()) != null;
	}

}
