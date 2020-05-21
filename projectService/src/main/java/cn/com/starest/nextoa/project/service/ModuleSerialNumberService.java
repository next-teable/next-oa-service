package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.project.domain.model.Module;

/**
 * @author dz
 */
public interface ModuleSerialNumberService {

	/**
	 * generate the code for specified module
	 *
	 * @param moduleFeature
	 * @return
	 */
	String generate(Module moduleFeature);

}
