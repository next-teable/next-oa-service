package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.News;
import in.clouthink.nextoa.repository.custom.NewsRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsRepository extends AbstractRepository<News>, NewsRepositoryCustom {

	Page<News> findByPublished(boolean published, Pageable pageable);

}
