package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Group;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserGroupRelationship;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupRelationshipRepository extends AbstractRepository<UserGroupRelationship> {

	Page<UserGroupRelationship> findByGroup(Group group, Pageable pageable);

	Page<UserGroupRelationship> findByUser(User user, Pageable pageable);

	long countByGroup(Group group);

}
