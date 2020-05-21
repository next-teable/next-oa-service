package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.LendingType;

/**
 * @author dz
 */
public interface LendingQueryRequest extends PageQueryRequest {

	//公司
	String getCompanyId();

	//费用类型
	String getAccountSubjectId();

	//借款对象
	LendingObject getLendingObject();

	//借还款类型
	LendingType getLendingType();

	//借款人
	String getLendingById();

	//借款单位
	String getLendingToId();

}
