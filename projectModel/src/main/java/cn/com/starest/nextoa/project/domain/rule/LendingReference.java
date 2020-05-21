package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Lending;

/**
 * @author dz
 */
public interface LendingReference {

	boolean hasReference(Lending target);

}
