package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.PaperQueryRequest;
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
