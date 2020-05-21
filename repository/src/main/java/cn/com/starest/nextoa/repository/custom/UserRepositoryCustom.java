package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.UserQueryRequest;
import cn.com.starest.nextoa.model.dtr.UsernameQueryRequest;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {

	Page<User> queryPage(Organization organization, UsernameQueryRequest queryRequest);

	Page<User> queryPage(SysRole role, UserQueryRequest queryRequest);

	Page<User> queryPage(UserQueryRequest queryRequest);

	Page<User> queryArchivedUsers(UserQueryRequest queryRequest);

}
