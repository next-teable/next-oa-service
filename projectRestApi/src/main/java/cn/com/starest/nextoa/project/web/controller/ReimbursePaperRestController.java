package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.request.DefaultReimburseContext;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ReimburseRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("报销管理")
@RestController
@RequestMapping({"/api/papers"})
public class ReimbursePaperRestController {

	@Autowired
	private ReimburseRestSupport reimburseRestSupport;

	@ApiOperation(value = "查看报销申请列表")
	@RequestMapping(value = "/reimburses", method = RequestMethod.GET)
	public Page<ReimburseSummary> listReimburses(ReimburseQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.listReimburses(request, user);
	}

	@ApiOperation(value = "查看报销申请")
	@RequestMapping(value = "/reimburses/{id}", method = RequestMethod.GET)
	public ReimburseDetail findReimburseById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.findReimburseById(id, user);
	}

	@ApiOperation(value = "创建报销申请")
	@RequestMapping(value = "/reimburses", method = RequestMethod.POST)
	public ReimburseSummary createReimburse(@Validated @RequestBody SaveReimburseParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.createReimburse(request, user);
	}

	@ApiOperation(value = "修改报销申请")
	@RequestMapping(value = "/reimburses/{id}", method = RequestMethod.POST)
	public ReimburseSummary updateReimburse(@PathVariable String id,
											@Validated @RequestBody SaveReimburseParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.updateReimburse(id, request, user);
	}

	@ApiOperation(value = "删除报销申请-只能删除未结算的报销申请")
	@RequestMapping(value = "/reimburses/{id}/delete", method = RequestMethod.POST)
	public void deleteReimburseById(@PathVariable String id, @RequestBody DefaultReimburseContext context) {
		User user = SecurityContexts.getContext().requireUser();
		reimburseRestSupport.deleteReimburse(id, context, user);
	}

	@ApiOperation(value = "在结算前修改报销单数据（目前只能修改待付款信息）")
	@RequestMapping(value = "/reimburses/{id}/pendingPayment", method = RequestMethod.POST)
	public void reimbursePendingPayment(@PathVariable String id,
										@RequestBody SaveReimbursePendingPaymentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		reimburseRestSupport.reimbursePendingPayment(id, request, user);
	}

	@ApiOperation(value = "准备结算报销申请 - 校验后给出当前借款金额提示(如果为空或者小于等于0则不提示)")
	@RequestMapping(value = "/reimburses/{id}/beforeSettle", method = RequestMethod.GET)
	public String beforeSettleReimburse(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.beforeSettleReimburse(id, user);
	}

	@ApiOperation(value = "准备结算报销申请-给出当前借款金额提示(如果为空或者小于等于0则不提示)")
	@RequestMapping(value = "/reimburses/beforeSettleByPaper/{paperId}", method = RequestMethod.GET)
	public List<String> beforeSettleReimburseByPaper(@PathVariable String paperId) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.beforeSettleReimburseByPaper(paperId, user);
	}

	@ApiOperation(value = "结算报销申请")
	@RequestMapping(value = "/reimburses/settleByPaper/{paperId}", method = RequestMethod.POST)
	public void settleReimburseByPaper(@PathVariable String paperId, @RequestBody DefaultReimburseContext context) {
		User user = SecurityContexts.getContext().requireUser();
		reimburseRestSupport.settleReimbursesByPaper(paperId, context, user);
	}

	@ApiOperation(value = "结算报销申请")
	@RequestMapping(value = "/reimburses/{id}/settle", method = RequestMethod.POST)
	public ReimburseSummary settleReimburse(@PathVariable String id, @RequestBody DefaultReimburseContext context) {
		User user = SecurityContexts.getContext().requireUser();
		return reimburseRestSupport.settleReimburse(id, context, user);
	}

}
