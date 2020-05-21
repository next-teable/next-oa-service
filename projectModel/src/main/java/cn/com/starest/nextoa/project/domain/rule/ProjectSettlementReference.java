package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;

/**
 * @author dz
 */
public interface ProjectSettlementReference {

	boolean hasReference(ProjectSettlement target);

}
