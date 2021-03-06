package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.NoticeCategory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeCategoryRepository extends AbstractRepository<NoticeCategory> {

	Page<NoticeCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
