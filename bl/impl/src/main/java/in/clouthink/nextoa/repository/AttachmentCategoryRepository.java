package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.AttachmentCategory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface AttachmentCategoryRepository extends AbstractRepository<AttachmentCategory> {

	Page<AttachmentCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
