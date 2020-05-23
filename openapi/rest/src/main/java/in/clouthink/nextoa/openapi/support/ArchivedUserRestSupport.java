package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.openapi.dto.UserDetail;
import in.clouthink.nextoa.openapi.dto.UserQueryParameter;
import in.clouthink.nextoa.openapi.dto.UserSummary;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest);

	UserDetail getArchivedUser(String id);
}
