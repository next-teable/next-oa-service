package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.AttachmentCategory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface AttachmentCategoryRepository extends AbstractRepository<AttachmentCategory> {

	Page<AttachmentCategory> findByCreatedBy(User createdBy, Pageable pageable);

}
