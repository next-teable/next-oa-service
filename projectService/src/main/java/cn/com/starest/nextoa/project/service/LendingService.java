package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;
import cn.com.starest.nextoa.project.domain.request.LendingQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveLendingRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface LendingService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param lending
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(Lending lending, User byWho);

	LendingAggregation findLendingAggregationById(String id, User byWho);

	Page<LendingAggregation> listLendingAggregations(LendingAggregationQueryRequest request, User byWho);

	Lending createLending(SaveLendingRequest request, User byWho);

	Lending updateLending(String id, SaveLendingRequest request, User byWho);

	Lending findLendingById(String id, User byWho);

	/**
	 * 查询借款还款列表
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<Lending> listLending(LendingQueryRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho （只有发起人能删除,已经结算的不能删除）
	 */
	void deleteLendingById(String id, User byWho);

}
