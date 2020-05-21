package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.SaveLendingRequest;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface LendingRestSupport {

	/**
	 * @param user
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User user);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	LendingSummary createLending(SaveLendingRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	LendingSummary updateLending(String id, SaveLendingRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	LendingDetail findLendingById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<LendingSummary> listLendings(LendingQueryParameter request, User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deleteLendingById(String id, User user);

	/**
	 * @param request
	 * @param user
	 * @return
	 */
	Page<LendingAggregationSummary> listLendingAggregationBySubContractor(LendingAggregationQueryParameter request,
																		  User user);

	/**
	 * @param request
	 * @param user
	 * @return
	 */
	Page<LendingAggregationSummary> listLendingAggregationByUser(LendingAggregationQueryParameter request, User user);

	/**
	 * @param id
	 * @param request
	 * @param user
	 * @return
	 */
	Page<LendingSummary> listLendingBySubContractor(String id, LendingQueryParameter request, User user);

	/**
	 * @param id
	 * @param request
	 * @param user
	 * @return
	 */
	Page<LendingSummary> listLendingByUser(String id, LendingQueryParameter request, User user);

}
