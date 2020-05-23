package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.PaperAction;
import cn.com.starest.nextoa.model.User;

/**
 * 快文内部服务,只提供给内部流转的时候调用,RestSupport不能调用本服务中的方法
 */
public interface PaperInnerService {

	void markPaperAsRead(String id, User user);

	void markPaperAsRead(Paper paper, User user);

	void handleStartPaperAction(PaperAction paperAction);

	void handleReplyPaperAction(PaperAction previousAction, PaperAction paperAction);

	void handleForwardPaperAction(PaperAction previousAction, PaperAction paperAction);

	void handleRevokePaperAction(PaperAction paperAction);

	void handleEndPaperAction(PaperAction paperAction);

	void handleTerminatePaperAction(PaperAction paperAction);
}
