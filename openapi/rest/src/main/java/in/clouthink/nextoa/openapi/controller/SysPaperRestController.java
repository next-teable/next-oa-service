package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.SysPaperRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@Api("快文库（系统管理中中监控所有的快文,但是不能操作,只能查看）")
@RestController
@RequestMapping("/api/system")
public class SysPaperRestController {

	@Autowired
	private SysPaperRestSupport sysPaperRestSupport;

	@ApiOperation(value = "列出系统的所有快文（不区分状态）,支持分页,支持动态查询")
	@RequestMapping(value = "/papers", method = RequestMethod.GET)
	public Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest) {
		return sysPaperRestSupport.listAllPapers(queryRequest);
	}

	@ApiOperation(value = "查看快文详情")
	@RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
	public PaperDetail getPaperDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return sysPaperRestSupport.getPaperDetail(id, user);
	}

	@ApiOperation(value = "终止快文-对应的所有任务为终止状态")
	@RequestMapping(value = "/papers/{id}/terminate", method = RequestMethod.POST)
	public void terminatePaper(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		sysPaperRestSupport.terminatePaper(id, user);
	}

	@ApiOperation(value = "查看快文权限（对发起人无效,发起人自动拥有所有权限）")
	@RequestMapping(value = "/papers/{id}/allowedActions", method = RequestMethod.GET)
	public List<String> getPaperAllowedActions(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return sysPaperRestSupport.getPaperAllowedActions(id, user);
	}

	@ApiOperation(value = "查看快文的阅读历史,支持分页,按阅读时间逆序排列")
	@RequestMapping(value = "/papers/{id}/readHistory", method = RequestMethod.GET)
	public Page<PaperReadSummary> getPaperReadHistory(@PathVariable String id, PaperActionQueryParameter queryRequest) {
		return sysPaperRestSupport.getPaperReadHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的打印历史,支持分页,按打印时间逆序排列")
	@RequestMapping(value = "/papers/{id}/printHistory", method = RequestMethod.GET)
	public Page<PaperPrintSummary> getPaperPrintHistory(@PathVariable String id,
                                                        PaperActionQueryParameter queryRequest) {
		return sysPaperRestSupport.getPaperPrintHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的流转情况,支持分页,按流转时间逆序排列")
	@RequestMapping(value = "/papers/{id}/transitionHistory", method = RequestMethod.GET)
	public Page<PaperTransitionSummary> getPaperTransitionHistory(@PathVariable String id,
																  PaperActionQueryParameter queryRequest) {
		return sysPaperRestSupport.getPaperTransitionHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的处理情况,支持分页,按处理时间逆序排列")
	@RequestMapping(value = "/papers/{id}/processHistory", method = RequestMethod.GET)
	public Page<PaperProcessSummary> getPaperProcessHistory(@PathVariable String id,
                                                            PaperActionQueryParameter queryRequest) {
		return sysPaperRestSupport.getPaperProcessHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的处理情况,支持分页,按处理时间逆序排列（不分页)")
	@RequestMapping(value = "/papers/{id}/processHistory/list", method = RequestMethod.GET)
	public List<PaperProcessSummary> getPaperProcessHistoryList(@PathVariable String id) {
		return sysPaperRestSupport.getPaperProcessHistory(id);
	}

	@ApiOperation(value = "快文消息跟踪,查看快文启动后的所有消息列表,只关心最近的状态,支持分页,按流转时间逆序排列")
	@RequestMapping(value = "/papers/{id}/messages", method = RequestMethod.GET)
	public Page<PaperMessageSummary> getPaperMessages(@PathVariable String id, PageQueryParameter queryRequest) {
		return sysPaperRestSupport.getPaperMessages(id, queryRequest);
	}

}
