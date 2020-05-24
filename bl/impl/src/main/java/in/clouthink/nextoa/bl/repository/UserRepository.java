package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.repository.custom.UserRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 */
public interface UserRepository extends AbstractRepository<User>, UserRepositoryCustom {

	User findByUsername(String username);

	List<User> findByUsernameLike(String username);

	User findByContactPhone(String contactPhone);

	User findByEmail(String email);

	Page<User> findByOrganization(Organization organization, Pageable pageable);

	long countByOrganization(Organization organization);

}
