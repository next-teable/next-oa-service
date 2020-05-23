package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.News;
import in.clouthink.nextoa.model.NewsReadHistory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NewsReadHistoryRepository extends AbstractRepository<NewsReadHistory> {

	Page<NewsReadHistory> findByNews(News news, Pageable pageable);

	NewsReadHistory findByNewsAndReadBy(News news, User user);

	int countByNews(News news);

}
