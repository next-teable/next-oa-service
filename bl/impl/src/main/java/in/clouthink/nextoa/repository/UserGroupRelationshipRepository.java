package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Group;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserGroupRelationship;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupRelationshipRepository extends AbstractRepository<UserGroupRelationship> {

	Page<UserGroupRelationship> findByGroup(Group group, Pageable pageable);

	Page<UserGroupRelationship> findByUser(User user, Pageable pageable);

	long countByGroup(Group group);

}
