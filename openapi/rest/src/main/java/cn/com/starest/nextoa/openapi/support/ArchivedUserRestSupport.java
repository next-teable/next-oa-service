package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.openapi.dto.UserDetail;
import cn.com.starest.nextoa.openapi.dto.UserQueryParameter;
import cn.com.starest.nextoa.openapi.dto.UserSummary;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest);

	UserDetail getArchivedUser(String id);
}
