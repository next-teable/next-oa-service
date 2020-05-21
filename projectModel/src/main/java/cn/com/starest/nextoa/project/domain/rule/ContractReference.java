package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Contract;

/**
 * @author dz
 */
public interface ContractReference {

	boolean hasReference(Contract target);

}
