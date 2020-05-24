package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.AppRole;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserRoleRelationship;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
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
