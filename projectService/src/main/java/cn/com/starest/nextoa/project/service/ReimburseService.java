package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.request.ReimburseContext;
import cn.com.starest.nextoa.project.domain.request.ReimburseQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveReimburseRequest;
import cn.com.starest.nextoa.project.domain.request.SaveReimbursePendingPaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ReimburseService {

	/**
	 * @param context
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(ReimburseContext context, User byWho);

	/**
	 * @param context
	 * @param reimburse
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(ReimburseContext context, Reimburse reimburse, User byWho);

	/**
	 * 创建报销
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	Reimburse createReimburse(SaveReimburseRequest request, User byWho);

	/**
	 * 修改报销明细（只有在快文草稿状态下的快文可以修改）
	 *
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	Reimburse updateReimburse(String id, SaveReimburseRequest request, User byWho);

	/**
	 * 修改报销明细（只有在快文草稿状态下的快文可以修改）
	 *
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	Reimburse updateReimburseSettlement(String id, SaveReimbursePendingPaymentRequest request, User byWho);

	/**
	 * 根据id查询报销明细
	 *
	 * @param id
	 * @param byWho
	 * @return
	 */
	Reimburse findReimburseById(String id, User byWho);

	/**
	 * 动态查询快文分页
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<Reimburse> listReimburses(ReimburseQueryRequest request, User byWho);

	/**
	 * 列出某快文关联的所有报销列表
	 *
	 * @param paperId
	 * @return
	 */
	List<Reimburse> listReimbursesByPaperId(String paperId);

	/**
	 * 列出某业务的报销列表（指定报销人）
	 *
	 * @param bizRefId
	 * @param payToId
	 * @return
	 */
	List<Reimburse> listReimbursesByBizRefIdAndPayToId(String bizRefId, String payToId);

	/**
	 * 删除报销（只能删除快文草稿状态中的报销,如果已经结算,或者流转中,则不能删除）
	 *
	 * @param id
	 * @param context
	 * @param byWho
	 */
	void deleteReimburseById(String id, ReimburseContext context, User byWho);


	/**
	 * 结算单条报销（先预检查是否满足条件,如果不满足,报销都不能结算）
	 *
	 * @param id      reimburse id
	 * @param context
	 * @param user
	 * @return
	 */
	Reimburse settleReimburse(String id, ReimburseContext context, User user);

	/**
	 * 批量结算报销（来自某个快文的所有报销列表,先预检查是否满足条件,如果不满足,整个报销列表都不能结算）
	 *
	 * @param paperId all the reimburses associated with the paper
	 * @param context
	 * @param byWho
	 */
	void settleBatchReimburses(String paperId, ReimburseContext context, User byWho);


	/**
	 * @param reimburseId
	 * @param request
	 * @param byWho
	 */
	void reimbursePendingPayment(String reimburseId, SaveReimbursePendingPaymentRequest request, User byWho);
}
