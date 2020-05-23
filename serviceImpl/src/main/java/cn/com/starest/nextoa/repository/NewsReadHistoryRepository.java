package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.NewsReadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NewsReadHistoryRepository extends AbstractRepository<NewsReadHistory> {

	Page<NewsReadHistory> findByNews(News news, Pageable pageable);

	NewsReadHistory findByNewsAndReadBy(News news, User user);

	int countByNews(News news);

}