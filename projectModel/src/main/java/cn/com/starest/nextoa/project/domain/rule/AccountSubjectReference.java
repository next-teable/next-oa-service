package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.AccountSubject;

/**
 * @author dz
 */
public interface AccountSubjectReference {

	boolean hasReference(AccountSubject target);

}
