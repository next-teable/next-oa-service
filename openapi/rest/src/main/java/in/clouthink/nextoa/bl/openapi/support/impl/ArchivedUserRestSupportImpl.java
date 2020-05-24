package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.UserDetail;
import in.clouthink.nextoa.bl.openapi.dto.UserQueryParameter;
import in.clouthink.nextoa.bl.openapi.dto.UserSummary;
import in.clouthink.nextoa.bl.openapi.support.ArchivedUserRestSupport;
import in.clouthink.nextoa.bl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 */
@Service
public class ArchivedUserRestSupportImpl implements ArchivedUserRestSupport {

	@Autowired
	private AccountService accountService;

	@Override
	public Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest) {
		queryRequest.setUserType(null);
		queryRequest.setEnabled(null);
		queryRequest.setOrganizationId(null);
		Page<User> userPage = accountService.listArchivedUsers(queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public UserDetail getArchivedUser(String id) {
		User user = accountService.findById(id);
		return UserDetail.from(user);
	}

}
