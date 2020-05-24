package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.UserQueryRequest;
import in.clouthink.nextoa.bl.request.UsernameQueryRequest;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {

	Page<User> queryPage(Organization organization, UsernameQueryRequest queryRequest);

	Page<User> queryPage(SysRole role, UserQueryRequest queryRequest);

	Page<User> queryPage(UserQueryRequest queryRequest);

	Page<User> queryArchivedUsers(UserQueryRequest queryRequest);

}
