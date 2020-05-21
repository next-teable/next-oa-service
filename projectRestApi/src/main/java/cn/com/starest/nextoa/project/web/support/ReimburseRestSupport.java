package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.ReimburseContext;
import cn.com.starest.nextoa.project.domain.request.SaveReimburseRequest;
import cn.com.starest.nextoa.project.web.dto.ReimburseDetail;
import cn.com.starest.nextoa.project.web.dto.ReimburseQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ReimburseSummary;
import cn.com.starest.nextoa.project.web.dto.SaveReimbursePendingPaymentParameter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ReimburseRestSupport {

	/**
	 * 创建报销单
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	ReimburseSummary createReimburse(SaveReimburseRequest request, User byWho);

	/**
	 * 修改报销单
	 *
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	ReimburseSummary updateReimburse(String id, SaveReimburseRequest request, User byWho);

	/**
	 * 查询报销单详情
	 *
	 * @param id
	 * @param byWho
	 * @return
	 */
	ReimburseDetail findReimburseById(String id, User byWho);

	/**
	 * 查询报销单列表
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<ReimburseSummary> listReimburses(ReimburseQueryParameter request, User byWho);

	/**
	 * @param id
	 * @param context
	 * @param user
	 */
	void deleteReimburse(String id, ReimburseContext context, User user);

	/**
	 * @param id
	 * @param context
	 * @param user
	 * @return
	 */
	ReimburseSummary settleReimburse(String id, ReimburseContext context, User user);

	/**
	 * @param id
	 * @param user
	 * @return
	 */
	String beforeSettleReimburse(String id, User user);

	/**
	 * @param paperId
	 * @param context
	 * @param user
	 */
	void settleReimbursesByPaper(String paperId, ReimburseContext context, User user);

	/**
	 * @param paperId
	 * @param user
	 * @return
	 */
	List<String> beforeSettleReimburseByPaper(String paperId, User user);

	/**
	 * @param id
	 * @param reqest
	 * @param user
	 */
	void reimbursePendingPayment(String id, SaveReimbursePendingPaymentParameter reqest, User user);
}
