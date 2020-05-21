package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserRoleRelationship;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRoleRelationshipRepository extends AbstractRepository<UserRoleRelationship> {

	Page<UserRoleRelationship> findByRole(AppRole role, Pageable pageable);

	Page<UserRoleRelationship> findByUser(User user, Pageable pageable);

	List<UserRoleRelationship> findListByUser(User user);

	UserRoleRelationship findByUserAndRole(User user, AppRole role);

	UserRoleRelationship findFirstByRole(AppRole appRole);

}
