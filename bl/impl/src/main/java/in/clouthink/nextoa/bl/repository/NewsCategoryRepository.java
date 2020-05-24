package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.NewsCategory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NewsCategoryRepository extends AbstractRepository<NewsCategory> {

	Page<NewsCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
