package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.User;

/**
 * 当Paper出现问题的时候,用这个服务来修复
 *
 * @author dz
 */
public interface PaperIssueService {

	void fixPaperTransitionIssues(String paperId, User user);

}
