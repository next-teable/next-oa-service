package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.model.NewsReadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NewsReadHistoryRepository extends AbstractRepository<NewsReadHistory> {

	Page<NewsReadHistory> findByNews(News news, Pageable pageable);

	NewsReadHistory findByNewsAndReadBy(News news, User user);

	int countByNews(News news);

}
