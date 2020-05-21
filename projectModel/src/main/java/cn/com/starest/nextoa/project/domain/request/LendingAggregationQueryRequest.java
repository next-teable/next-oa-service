package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.LendingObject;

/**
 * @author dz
 */
public interface LendingAggregationQueryRequest extends PageQueryRequest {

	//借款人
	String getLendingById();

	//借款单位
	String getSubContractorId();

	//借款对象
	LendingObject getLendingObject();

}
