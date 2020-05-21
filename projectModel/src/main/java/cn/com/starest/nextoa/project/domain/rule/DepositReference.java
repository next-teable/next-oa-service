package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Deposit;

/**
 * @author dz
 */
public interface DepositReference {

	boolean hasReference(Deposit target);

}
