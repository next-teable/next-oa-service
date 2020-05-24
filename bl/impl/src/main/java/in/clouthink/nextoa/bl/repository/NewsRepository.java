package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.repository.custom.NewsRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsRepository extends AbstractRepository<News>, NewsRepositoryCustom {

	Page<News> findByPublished(boolean published, Pageable pageable);

}
