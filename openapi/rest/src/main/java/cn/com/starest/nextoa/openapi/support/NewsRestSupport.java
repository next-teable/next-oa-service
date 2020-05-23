package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
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
