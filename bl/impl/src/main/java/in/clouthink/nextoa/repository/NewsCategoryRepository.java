package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.NewsCategory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsCategoryRepository extends AbstractRepository<NewsCategory> {

	Page<NewsCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
