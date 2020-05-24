package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.openapi.dto.UserDetail;
import in.clouthink.nextoa.bl.openapi.dto.UserQueryParameter;
import in.clouthink.nextoa.bl.openapi.dto.UserSummary;
import org.springframework.data.domain.Page;

/**
 */
public interface ArchivedUserRestSupport {

	Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest);

	UserDetail getArchivedUser(String id);
}
