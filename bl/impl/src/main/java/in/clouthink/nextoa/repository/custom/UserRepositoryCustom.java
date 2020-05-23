package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.model.SysRole;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.UserQueryRequest;
import in.clouthink.nextoa.model.dtr.UsernameQueryRequest;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {

	Page<User> queryPage(Organization organization, UsernameQueryRequest queryRequest);

	Page<User> queryPage(SysRole role, UserQueryRequest queryRequest);

	Page<User> queryPage(UserQueryRequest queryRequest);

	Page<User> queryArchivedUsers(UserQueryRequest queryRequest);

}
