package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.Company;

/**
 * @author dz
 */
public interface CompanyReference {

	boolean hasReference(Company target);

}
