package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.*;
import in.clouthink.nextoa.model.ContactGroup;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserContactGroupRelationship;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserContactGroupRelationshipRepository extends AbstractRepository<UserContactGroupRelationship> {

	Page<UserContactGroupRelationship> findByContactGroup(ContactGroup contactGroup, Pageable pageable);

	Page<UserContactGroupRelationship> findByUser(User user, Pageable pageable);

	int countByContactGroup(ContactGroup contactGroup);

	UserContactGroupRelationship findByContactGroupAndUser(ContactGroup contactGroup, User user);

	void deleteByUser(User user);

}
