package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.NoticeCategory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeCategoryRepository extends AbstractRepository<NoticeCategory> {

	Page<NoticeCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
