package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PaperQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface PaperRepositoryCustom {

	Page<Paper> queryPage(User creator,
						  PaperQueryRequest request,
						  PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	long queryCount(User creator,
					PaperQueryRequest request,
					PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus);

	void updateReadCounter(String id, int readCounter);

	void markPaperBusinessComplete(Paper paper);
}
