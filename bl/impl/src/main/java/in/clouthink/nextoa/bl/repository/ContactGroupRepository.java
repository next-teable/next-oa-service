package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.ContactGroup;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
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
