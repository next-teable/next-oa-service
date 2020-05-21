package cn.com.starest.nextoa.service.impl;

import cn.com.starest.nextoa.exception.ContactGroupException;
import cn.com.starest.nextoa.exception.ContactGroupNotFoundException;
import cn.com.starest.nextoa.model.ContactGroup;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserContactGroupRelationship;
import cn.com.starest.nextoa.model.dtr.ContactGroupQueryRequest;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.repository.ContactGroupRepository;
import cn.com.starest.nextoa.repository.RecentUserRelationshipRepository;
import cn.com.starest.nextoa.repository.UserContactGroupRelationshipRepository;
import cn.com.starest.nextoa.repository.UserRepository;
import cn.com.starest.nextoa.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactGroupRepository contactGroupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserContactGroupRelationshipRepository userContactGroupRelationshipRepository;

	@Autowired
	private RecentUserRelationshipRepository recentUserRelationshipRepository;

	@Override
	public Page<ContactGroup> listContactGroups(ContactGroupQueryRequest request, User byWho) {
		PageRequest pageRequest = new PageRequest(request.getStart(),
												  request.getLimit(),
												  new Sort(Sort.Direction.ASC, "name"));
		if (StringUtils.isEmpty(request.getName())) {
			return contactGroupRepository.findByCreatedBy(byWho, pageRequest);
		}
		else {
			return contactGroupRepository.findByNameLikeAndCreatedBy(request.getName(), byWho, pageRequest);
		}
	}

	@Override
	public ContactGroup createContactGroup(String name, User byWho) {
		if (StringUtils.isEmpty(name)) {
			throw new ContactGroupException("组名不能为空");
		}

		ContactGroup groupByName = contactGroupRepository.findByCreatedByAndName(byWho, name);
		if (groupByName != null) {
			throw new ContactGroupException("组名不能重复");
		}

		ContactGroup contactGroup = new ContactGroup();
		contactGroup.setName(name);
		contactGroup.setCreatedAt(new Date());
		contactGroup.setCreatedBy(byWho);
		return contactGroupRepository.save(contactGroup);
	}

	@Override
	public ContactGroup findContactGroupById(String id, User byWho) {
		ContactGroup result = contactGroupRepository.findById(id);
		//TODO
		//validate the data permission
		return result;
	}

	@Override
	public void updateContactGroup(String id, String name, User byWho) {
		if (StringUtils.isEmpty(name)) {
			throw new ContactGroupException("组名不能为空");
		}
		ContactGroup contactGroup = contactGroupRepository.findById(id);
		if (contactGroup == null) {
			throw new ContactGroupNotFoundException(id);
		}

		if (!contactGroup.getCreatedBy().getId().equals(byWho.getId())) {
			throw new ContactGroupException("只能修改自己的联系人分组.");
		}

		ContactGroup groupByName = contactGroupRepository.findByCreatedByAndName(byWho, name);
		if (groupByName != null && !groupByName.getId().equals(id)) {
			throw new ContactGroupException("组名不能重复");
		}

		contactGroup.setName(name);
		contactGroup.setModifiedAt(new Date());
		contactGroupRepository.save(contactGroup);
	}

	@Override
	public void deleteContactGroup(String id, User byWho) {
		ContactGroup contactGroup = contactGroupRepository.findById(id);
		if (contactGroup == null) {
			throw new ContactGroupNotFoundException(id);
		}

		if (!contactGroup.getCreatedBy().getId().equals(byWho.getId())) {
			throw new ContactGroupException("只能修改自己的联系人分组.");
		}

		if (userContactGroupRelationshipRepository.countByContactGroup(contactGroup) > 0) {
			throw new ContactGroupException("该联系人组已绑定用户,请解除用户绑定后再删除.");
		}

		contactGroupRepository.delete(id);
	}

	@Override
	public void addUsersToGroup(String id, String[] userIds, User byWho) {
		ContactGroup contactGroup = contactGroupRepository.findById(id);
		if (contactGroup == null) {
			throw new ContactGroupNotFoundException(id);
		}
		List<UserContactGroupRelationship> relationshipList = new ArrayList<>();
		for (String userId : userIds) {
			User user = userRepository.findById(userId);
			if (user == null) {
				continue;
			}
			UserContactGroupRelationship relationship = userContactGroupRelationshipRepository.findByContactGroupAndUser(
					contactGroup,
					user);
			if (relationship == null) {
				relationship = new UserContactGroupRelationship();
				relationship.setContactGroup(contactGroup);
				relationship.setUser(user);
				relationship.setCreatedBy(byWho);
				relationship.setCreatedAt(new Date());
				relationshipList.add(relationship);
			}
		}
		userContactGroupRelationshipRepository.save(relationshipList);
	}

	@Override
	public void removeUsersFromGroup(String id, String[] userIds, User byWho) {
		ContactGroup contactGroup = contactGroupRepository.findById(id);
		if (contactGroup == null) {
			throw new ContactGroupNotFoundException(id);
		}
		List<UserContactGroupRelationship> relationshipList = new ArrayList<>();
		for (String userId : userIds) {
			User user = userRepository.findById(userId);
			if (user == null) {
				continue;
			}
			UserContactGroupRelationship relationship = userContactGroupRelationshipRepository.findByContactGroupAndUser(
					contactGroup,
					user);
			if (relationship != null) {
				relationshipList.add(relationship);
			}
		}
		userContactGroupRelationshipRepository.delete(relationshipList);
	}

	@Override
	public Page<User> listUsersOfContactGroups(String id, PageQueryRequest request) {
		ContactGroup contactGroup = contactGroupRepository.findById(id);
		if (contactGroup == null) {
			throw new ContactGroupNotFoundException(id);
		}
		Pageable pageable = new PageRequest(request.getStart(), request.getLimit());
		Page<UserContactGroupRelationship> relationships = userContactGroupRelationshipRepository.findByContactGroup(
				contactGroup,
				pageable);
		List<User> users = relationships.getContent()
										.stream()
										.map(UserContactGroupRelationship::getUser)
										.collect(Collectors.toList());
		return new PageImpl<>(users, pageable, relationships.getTotalElements());
	}

	@Override
	public List<User> listRecentUsers(User user) {
		return recentUserRelationshipRepository.findListByUserOrderByCreatedAtDesc(user)
											   .stream()
											   .map(relationship -> relationship.getRecentUser())
											   .collect(Collectors.toList());
	}

}
