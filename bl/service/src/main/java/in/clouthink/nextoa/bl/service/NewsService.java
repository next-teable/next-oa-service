package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.model.NewsReadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.NewsQueryRequest;
import in.clouthink.nextoa.bl.request.SaveNewsRequest;
import in.clouthink.nextoa.shared.domain.request.PageQueryRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface NewsService {

	Page<News> listNews(NewsQueryRequest queryParameter);

	News findNewsById(String id);

	void markNewsAsRead(News news, User user);

	boolean isNewsReadByUser(News news, User user);

	int countNewsReadHistory(News news);

	News createNews(SaveNewsRequest news, User user);

	void updateNews(String id, SaveNewsRequest news, User user);

	void deleteNews(String id, User user);

	void publishNews(String id, User user);

	void unpublishNews(String id, User user);

	Page<NewsReadHistory> listReadHistory(String id, PageQueryRequest queryRequest);

	String getImageFileObjectId(News news);

	public void deleteAttachment(String id, String fileId, User user);

}
