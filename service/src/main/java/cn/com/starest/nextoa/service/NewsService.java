package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.NewsReadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NewsQueryRequest;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.model.dtr.SaveNewsRequest;
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
