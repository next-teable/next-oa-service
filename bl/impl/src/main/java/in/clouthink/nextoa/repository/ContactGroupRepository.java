package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.ContactGroup;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
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
