package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface NewsRestSupport {

	Page<NewsSummary> listNews(NewsQueryParameter queryRequest);

	NewsDetail getNewsDetail(String id);

	String createNews(SaveNewsParameter request, User user);

	void updateNews(String id, SaveNewsParameter request, User user);

	void deleteNews(String id, User user);

	void publishNews(String id, User user);

	void unpublishNews(String id, User user);

	Page<ReadSummary> listReadHistory(String id, PageQueryParameter queryParameter);

	void deleteAttachment(String id, String fileId, User user);
}
