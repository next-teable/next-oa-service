package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.Module;

import java.util.List;

/**
 * @author dz
 */
public interface GrantModuleRequest {

	/**
	 * @return module
	 */
	Module getModule();

	/**
	 * @return action codes
	 */
	List<String> getActions();

}
