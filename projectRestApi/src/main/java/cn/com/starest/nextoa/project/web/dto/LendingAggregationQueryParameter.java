package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;

/**
 * @author dz
 */
public class LendingAggregationQueryParameter extends PageQueryParameter implements LendingAggregationQueryRequest {

	//借款对象
	LendingObject lendingObject;

	//借款人
	String lendingById;

	//借款单位
	String subContractorId;

	@Override
	public LendingObject getLendingObject() {
		return lendingObject;
	}

	public void setLendingObject(LendingObject lendingObject) {
		this.lendingObject = lendingObject;
	}

	@Override
	public String getLendingById() {
		return lendingById;
	}

	public void setLendingById(String lendingById) {
		this.lendingById = lendingById;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}
}
