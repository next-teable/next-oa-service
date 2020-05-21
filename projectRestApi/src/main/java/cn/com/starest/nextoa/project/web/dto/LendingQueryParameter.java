package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.LendingType;
import cn.com.starest.nextoa.project.domain.request.LendingQueryRequest;

/**
 * @author dz
 */
public class LendingQueryParameter extends PageQueryParameter implements LendingQueryRequest {

	//费用类型
	String accountSubjectId;

	//借款对象
	LendingObject lendingObject;

	//借还款类型
	LendingType lendingType;

	//借款公司
	String companyId;

	//借款人
	String lendingById;

	//借款单位
	String lendingToId;

	@Override
	public String getAccountSubjectId() {
		return accountSubjectId;
	}

	public void setAccountSubjectId(String accountSubjectId) {
		this.accountSubjectId = accountSubjectId;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getLendingById() {
		return lendingById;
	}

	public void setLendingById(String lendingById) {
		this.lendingById = lendingById;
	}

	@Override
	public String getLendingToId() {
		return lendingToId;
	}

	public void setLendingToId(String lendingToId) {
		this.lendingToId = lendingToId;
	}

	@Override
	public LendingObject getLendingObject() {
		return lendingObject;
	}

	public void setLendingObject(LendingObject lendingObject) {
		this.lendingObject = lendingObject;
	}

	@Override
	public LendingType getLendingType() {
		return lendingType;
	}

	public void setLendingType(LendingType lendingType) {
		this.lendingType = lendingType;
	}

}
