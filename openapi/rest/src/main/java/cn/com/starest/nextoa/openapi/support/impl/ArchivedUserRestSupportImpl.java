package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.UserDetail;
import cn.com.starest.nextoa.openapi.dto.UserQueryParameter;
import cn.com.starest.nextoa.openapi.dto.UserSummary;
import cn.com.starest.nextoa.openapi.support.ArchivedUserRestSupport;
import cn.com.starest.nextoa.service.AccountService;
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
