package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.User;

/**
 * 当Paper出现问题的时候,用这个服务来修复
 *
 * @author dz
 */
public interface PaperIssueService {

	void fixPaperTransitionIssues(String paperId, User user);

}
