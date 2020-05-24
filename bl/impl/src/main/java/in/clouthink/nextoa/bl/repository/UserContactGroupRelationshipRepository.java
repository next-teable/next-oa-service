package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.ContactGroup;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserContactGroupRelationship;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserContactGroupRelationshipRepository extends AbstractRepository<UserContactGroupRelationship> {

	Page<UserContactGroupRelationship> findByContactGroup(ContactGroup contactGroup, Pageable pageable);

	Page<UserContactGroupRelationship> findByUser(User user, Pageable pageable);

	int countByContactGroup(ContactGroup contactGroup);

	UserContactGroupRelationship findByContactGroupAndUser(ContactGroup contactGroup, User user);

	void deleteByUser(User user);

}
