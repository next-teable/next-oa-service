package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.rule.ProjectSettlementReference;
import cn.com.starest.nextoa.project.repository.ReimburseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectSettlementReferenceImpl implements ProjectSettlementReference {

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Override
	public boolean hasReference(ProjectSettlement target) {
		return reimburseRepository.findFirstByBizRefId(target.getId()) != null;
	}

}
