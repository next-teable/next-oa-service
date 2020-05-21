package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.License;

/**
 * @author dz
 */
public interface LicenseReference {

	boolean hasReference(License target);

}
