package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Group;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserGroupRelationship;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupRelationshipRepository extends AbstractRepository<UserGroupRelationship> {

	Page<UserGroupRelationship> findByGroup(Group group, Pageable pageable);

	Page<UserGroupRelationship> findByUser(User user, Pageable pageable);

	long countByGroup(Group group);

}
