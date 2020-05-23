package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.PaperRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("我的快文（公文）")
@RestController
@RequestMapping("/api")
public class PaperRestController {

	@Autowired
	private PaperRestSupport paperRestSupport;

	@ApiOperation(value = "列出我的所有快文（不区分状态）,支持分页,支持动态查询")
	@RequestMapping(value = "/papers", method = RequestMethod.GET)
	public Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.listAllPapers(queryRequest, user);
	}

	@ApiOperation(value = "列出我的快文（只是草稿状态）,支持分页,支持动态查询")
	@RequestMapping(value = "/papers/draft", method = RequestMethod.GET)
	public Page<PaperSummary> listDraftPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.listDraftPapers(queryRequest, user);
	}

	@ApiOperation(value = "列出我的快文（流转中）,支持分页,支持动态查询")
	@RequestMapping(value = "/papers/processing", method = RequestMethod.GET)
	public Page<PaperSummary> listProcessingPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.listProcessingPapers(queryRequest, user);
	}

	@ApiOperation(value = "列出我的快文（撤回状态）,支持分页,支持动态查询")
	@RequestMapping(value = "/papers/revoked", method = RequestMethod.GET)
	public Page<PaperSummary> listRevokedPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.listRevokedPapers(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的所有快文（不区分状态）,支持动态查询")
	@RequestMapping(value = "/papers/countOfAll", method = RequestMethod.GET)
	public long countOfAllPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.countOfAllPapers(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的快文（只是草稿状态）,支持动态查询")
	@RequestMapping(value = "/papers/countOfDraft", method = RequestMethod.GET)
	public long countOfDraftPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.countOfDraftPapers(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的快文（流转中）,支持动态查询")
	@RequestMapping(value = "/papers/countOfProcessing", method = RequestMethod.GET)
	public long countOfProcessingPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.countOfProcessingPapers(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的快文（撤回状态）,支持动态查询")
	@RequestMapping(value = "/papers/countOfRevoked", method = RequestMethod.GET)
	public long countOfRevokedPapers(PaperQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.countOfRevokedPapers(queryRequest, user);
	}

	@ApiOperation(value = "查看快文详情")
	@RequestMapping(value = "/papers/{id}", method = RequestMethod.GET)
	public PaperDetail getPaperDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.getPaperDetail(id, user);
	}

	@ApiOperation(value = "查看快文权限（对发起人无效,发起人自动拥有所有权限）")
	@RequestMapping(value = "/papers/{id}/allowedActions", method = RequestMethod.GET)
	public List<String> getPaperAllowedActions(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.getPaperAllowedActions(id, user);
	}

	@ApiOperation(value = "新增快文（草稿状态,可以反复修改）")
	@RequestMapping(value = "/papers", method = RequestMethod.POST)
	public String createPaper(@RequestBody SavePaperParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.createPaper(request, user);
	}

	@ApiOperation(value = "修改快文（草稿,撤回的状态才可以修改）")
	@RequestMapping(value = "/papers/{id}", method = RequestMethod.POST)
	public void updatePaper(@PathVariable String id, @RequestBody SavePaperParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.updatePaper(id, request, user);
	}

	@ApiOperation(value = "删除快文（草稿,撤回状态的才可以删除）")
	@RequestMapping(value = "/papers/{id}", method = RequestMethod.DELETE)
	public void deletePaper(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.deletePaper(id, user);
	}

	@ApiOperation(value = "提交快文到流程中（草稿,撤回状态的可以提交）")
	@RequestMapping(value = "/papers/{id}/start", method = RequestMethod.POST)
	public void startPaper(@PathVariable String id, @RequestBody StartPaperParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.startPaper(id, request, user);
	}

	@ApiOperation(value = "撤回快文（流转中,且没有被处理的）")
	@RequestMapping(value = "/papers/{id}/revoke", method = RequestMethod.POST)
	public void revokePaper(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.revokePaper(id, user);
	}

	@ApiOperation(value = "标记快文为已读")
	@RequestMapping(value = "/papers/{id}/read", method = RequestMethod.POST)
	public void markPaperAsRead(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.markPaperAsRead(id, user);
	}

	@ApiOperation(value = "打印快文（需要有打印权限）")
	@RequestMapping(value = "/papers/{id}/print", method = RequestMethod.POST)
	public PaperDetail printPaper(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.printPaper(id, user);
		return paperRestSupport.getPaperDetail(id, user);
	}

	@ApiOperation(value = "回复快文")
	@RequestMapping(value = "/papers/{id}/reply", method = RequestMethod.POST)
	public void replyPaper(@PathVariable String id, @RequestBody ReplyPaperParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.replyPaper(id, request, user);
	}

	@ApiOperation(value = "转发快文（需要有转发权限）")
	@RequestMapping(value = "/papers/{id}/forward", method = RequestMethod.POST)
	public void forwardPaper(@PathVariable String id, @RequestBody ForwardPaperParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.forwardPaper(id, request, user);
	}

	@ApiOperation(value = "结束快文-对应的任务为结束状态（注意:回复,转发后,用户的任务也为结束状态）")
	@RequestMapping(value = "/papers/{id}/done", method = RequestMethod.POST)
	public void markPaperAsDone(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.markPaperAsDone(id, user);
	}

	@ApiOperation(value = "复制快文（原快文允许再处理）")
	@RequestMapping(value = "/papers/{id}/copy", method = RequestMethod.POST)
	public PaperDetail copyPaper(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.copyPaperDetail(id, user);
	}

	@ApiOperation(value = "查看快文的阅读历史,支持分页,按阅读时间逆序排列")
	@RequestMapping(value = "/papers/{id}/readHistory", method = RequestMethod.GET)
	public Page<PaperReadSummary> getPaperReadHistory(@PathVariable String id, PaperActionQueryParameter queryRequest) {
		return paperRestSupport.getPaperReadHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的打印历史,支持分页,按打印时间逆序排列")
	@RequestMapping(value = "/papers/{id}/printHistory", method = RequestMethod.GET)
	public Page<PaperPrintSummary> getPaperPrintHistory(@PathVariable String id,
                                                        PaperActionQueryParameter queryRequest) {
		return paperRestSupport.getPaperPrintHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的流转情况,支持分页,按流转时间逆序排列")
	@RequestMapping(value = "/papers/{id}/transitionHistory", method = RequestMethod.GET)
	public Page<PaperTransitionSummary> getPaperTransitionHistory(@PathVariable String id,
																  PaperActionQueryParameter queryRequest) {
		return paperRestSupport.getPaperTransitionHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看哪些用户进行了结束快文操作,支持分页,按流转时间逆序排列")
	@RequestMapping(value = "/papers/{id}/endHistory", method = RequestMethod.GET)
	public Page<PaperTransitionSummary> getPaperEndHistory(@PathVariable String id,
														   PaperActionQueryParameter queryRequest) {
		return paperRestSupport.getPaperEndHistory(id, queryRequest);
	}

	@ApiOperation(value = "快文消息跟踪,查看快文启动后的所有消息列表,只关心最近的状态,支持分页,按流转时间逆序排列")
	@RequestMapping(value = "/papers/{id}/messages", method = RequestMethod.GET)
	public Page<PaperMessageSummary> getPaperMessages(@PathVariable String id, PageQueryParameter queryRequest) {
		return paperRestSupport.getPaperMessages(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的处理意见,支持分页,按处理时间逆序排列")
	@RequestMapping(value = "/papers/{id}/processHistory", method = RequestMethod.GET)
	public Page<PaperProcessSummary> getPaperProcessHistory(@PathVariable String id,
                                                            PaperActionQueryParameter queryRequest) {
		return paperRestSupport.getPaperProcessHistory(id, queryRequest);
	}

	@ApiOperation(value = "查看快文的处理意见,按处理时间逆序排列（不分页)")
	@RequestMapping(value = "/papers/{id}/processHistory/list", method = RequestMethod.GET)
	public List<PaperProcessSummary> getPaperProcessHistoryList(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return paperRestSupport.getPaperProcessHistory(id, user);
	}

	@ApiOperation(value = "删除快文附件")
	@RequestMapping(value = "/papers/{paperId}/files/{fileId}", method = RequestMethod.DELETE)
	public void deletePaperAttachment(@PathVariable String paperId, @PathVariable String fileId) {
		User user = SecurityContexts.getContext().requireUser();
		paperRestSupport.deletePaperAttachment(paperId, fileId, user);
	}

}
