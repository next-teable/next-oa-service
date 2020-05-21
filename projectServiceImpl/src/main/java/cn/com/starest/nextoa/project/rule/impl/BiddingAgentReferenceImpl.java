package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.BiddingAgent;
import cn.com.starest.nextoa.project.domain.rule.BiddingAgentReference;
import cn.com.starest.nextoa.project.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class BiddingAgentReferenceImpl implements BiddingAgentReference {

	@Autowired
	DepositRepository depositRepository;

	@Override
	public boolean hasReference(BiddingAgent target) {
		return depositRepository.findFirstByBiddingAgent(target) != null;
	}

}
