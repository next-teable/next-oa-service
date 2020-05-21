package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Order;

/**
 * @author dz
 */
public interface OrderReference {

	boolean hasReference(Order target);

}
