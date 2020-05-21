package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.FrameworkContractType;

/**
 * @author dz
 */
public interface FrameworkContractQueryRequest extends NamedPageQueryRequest {

	FrameworkContractType getType();

}
