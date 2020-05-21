package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.SubOrder;

/**
 * @author dz
 */
public interface SubOrderReference {

	boolean hasReference(SubOrder target);

}
