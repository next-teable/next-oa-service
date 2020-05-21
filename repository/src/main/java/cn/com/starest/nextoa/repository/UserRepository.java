package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.custom.UserRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
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
