package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.FirstParty;

/**
 * @author dz
 */
public interface FirstPartyReference {

	boolean hasReference(FirstParty target);

}
