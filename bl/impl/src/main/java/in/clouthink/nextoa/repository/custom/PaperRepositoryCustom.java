package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.PaperQueryRequest;
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
