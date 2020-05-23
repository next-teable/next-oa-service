package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.PaperAction;
import in.clouthink.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.*;
import in.clouthink.nextoa.model.dtr.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 */
public interface PaperService {

	Page<Paper> listPapers(PaperQueryRequest queryRequest);

	Page<Paper> listPapers(PaperQueryRequest queryRequest,
						   PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
						   User user);

	long countOfPapers(PaperQueryRequest queryRequest,
					   PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
					   User user);

	/**
	 * 可查看所有paper,无权限控制,仅用于在list中需要转化paper的summary的时候使用
	 *
	 * @param id
	 * @return
	 */
	Paper findPaperById(String id);

	/**
	 * 只能查看user拥有的paper
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	Paper findPaperById(String id, User user);

	Page<PaperAction> getPaperActionHistory(String id, PaperActionQueryRequest queryRequest);

	/**
	 * Please seee <code>PaperService#getPaperProcessHistoryList</code>
	 *
	 * @param id
	 * @param queryRequest
	 * @return
	 */
	List<PaperAction> getPaperActionHistoryList(String id, PaperActionQueryRequest queryRequest);

	/**
	 * 处理意见历史
	 *
	 * @param id
	 * @return
	 */
	List<PaperAction> getPaperProcessHistoryList(String id, User user);

	Paper createPaper(SavePaperRequest request, User user);

	Paper copyPaper(String id, User user);

	void updatePaper(String id, SavePaperRequest request, User user);

	void deletePaper(String id, User user);

	void revokePaper(String id, User user);

	void startPaper(String id, StartPaperRequest request, User user);

	void printPaper(String id, User user);

	void replyPaper(String id, ReplyPaperRequest request, User user);

	void forwardPaper(String id, ForwardPaperRequest request, User user);

	void endPaper(String id, User user);

	void endPaper(Paper paper, User user);

	void terminatePaper(String id, User user);

	void terminatePaper(Paper paper, User user);

	PaperAction findPaperActionById(String id);

	boolean isRead(Paper paper, User user);

	boolean isFavorite(Paper paper, User user);

	void deletePaperAttachment(String paperId, String fileId, User user);

	void markPaperBusinessComplete(Paper paper);

}
