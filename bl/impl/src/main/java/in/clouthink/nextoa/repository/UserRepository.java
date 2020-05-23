package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.custom.UserRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
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
