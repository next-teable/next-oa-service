package in.clouthink.nextoa.service.impl;

import in.clouthink.nextoa.exception.UserOpinionException;
import in.clouthink.nextoa.exception.UserOpinionNotFoundException;
import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserOpinion;
import in.clouthink.nextoa.model.dtr.ChangeUserProfileRequest;
import in.clouthink.nextoa.repository.UserOpinionRepository;
import in.clouthink.nextoa.service.AccountService;
import in.clouthink.nextoa.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserOpinionRepository userOpinionRepository;

	@Override
	public User findUserById(String userId) {
		return accountService.findById(userId);
	}

	@Override
	public User changeUserProfile(String userId, ChangeUserProfileRequest request) {
		return accountService.changeUserProfile(userId, request);
	}

	@Override
	public User changeUserPassword(String userId, String oldPassword, String newPassword) {
		return accountService.changePassword(userId, oldPassword, newPassword);
	}

	@Override
	public List<Organization> getOrganizationsOfUser(User user) {
		return user.getOrganizations();
	}

	@Override
	public List<UserOpinion> getUserOpinions(User user) {
		return userOpinionRepository.findListByCreatedByOrderByCreatedAtAsc(user);
	}

	@Override
	public UserOpinion findUserOpinionById(String id, User user) {
		UserOpinion result = userOpinionRepository.findById(id);
		//TODO add permission check
		return result;
	}

	@Override
	public UserOpinion createUserOpinion(String content, User user) {
		checkUserOpinionContent(content);

		if (userOpinionRepository.countByCreatedBy(user) > 20) {
			throw new UserOpinionException("最多只能创建20条个人常用意见.");
		}

		UserOpinion userOpinion = new UserOpinion();
		userOpinion.setContent(content);
		userOpinion.setCreatedBy(user);
		userOpinion.setCreatedAt(new Date());
		return userOpinionRepository.save(userOpinion);
	}

	@Override
	public UserOpinion updateUserOpinion(String id, String content, User user) {
		checkUserOpinionContent(content);

		UserOpinion userOpinion = userOpinionRepository.findById(id);
		if (userOpinion == null) {
			throw new UserOpinionNotFoundException(id);
		}

		if (userOpinion.getCreatedBy() == null || user == null) {
			throw new UserOpinionException("只能修改自己的常用意见.");
		}
		if (!userOpinion.getCreatedBy().getId().equals(user.getId())) {
			throw new UserOpinionException("只能修改自己的常用意见.");
		}

		userOpinion.setContent(content);
		return userOpinionRepository.save(userOpinion);
	}

	@Override
	public void deleteUserOpinion(String id, User user) {
		UserOpinion userOpinion = userOpinionRepository.findById(id);
		if (userOpinion == null) {
			return;
		}

		if (userOpinion.getCreatedBy() == null || user == null) {
			throw new UserOpinionException("只能删除自己的常用意见.");
		}
		if (!userOpinion.getCreatedBy().getId().equals(user.getId())) {
			throw new UserOpinionException("只能删除自己的常用意见.");
		}

		userOpinionRepository.delete(userOpinion);
	}

	@Override
	public void updateUserAvatar(String fileObjectId, User byWho) {
		accountService.changeUserAvatar(byWho.getId(), fileObjectId);
	}

	private void checkUserOpinionContent(String content) {
		if (StringUtils.isEmpty(content)) {
			throw new UserOpinionException("用户常用意见内容不能为空.");
		}
		if (content.length() > 500) {
			throw new UserOpinionException("用户常用意见内容不能超过500个字符串.");
		}
	}

}
