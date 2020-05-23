package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.ContactGroup;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface ContactGroupRepository extends AbstractRepository<ContactGroup> {

	Page<ContactGroup> findByCreatedBy(User user, Pageable pageable);

	Page<ContactGroup> findByNameLikeAndCreatedBy(String name, User user, Pageable pageable);

	ContactGroup findByCreatedByAndName(User user, String name);
}