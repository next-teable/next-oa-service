package cn.com.starest.nextoa.service.impl;

import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserType;
import cn.com.starest.nextoa.repository.*;
import cn.com.starest.nextoa.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 */
@Service
public class MigrationServiceImpl implements MigrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private PaperRepository paperRepository;

	@Autowired
	private PaperTransitionRepository paperTransitionRepository;

	@Override
	public void migrateUserOrganizationRelationships(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		userRepository.findAll().forEach(user -> {
			Organization organization = user.getOrganization();
			if (organization == null) {
				return;
			}
			user.setOrganization(null);
			user.setOrganizations(Arrays.asList(organization));
			userRepository.save(user);
		});
	}

	@Override
	public void updateOrganizationLeafStatus(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		organizationRepository.findAll().forEach(org -> {
			org.setLeaf((organizationRepository.countByParent(org) == 0));
			organizationRepository.save(org);
		});
	}

	@Override
	public void fixAppUserNullOrganization(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		Organization organization = organizationRepository.findByParentAndName(null, "临时部门");

		userRepository.findAll().forEach(user -> {
			if (user.getUserType() == UserType.APPUSER &&
				(user.getOrganizations() == null || user.getOrganizations().isEmpty())) {
				user.setOrganizations(Arrays.asList(organization));
				userRepository.save(user);
			}
		});
	}

	@Override
	public void updatePaperVersion(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		paperRepository.findAll().forEach(paper -> {
			if (paper.getVersion() == 0) {
				paper.setVersion(1);
				paperRepository.save(paper);
			}
		});
	}

	@Override
	public void updatePaperTransitionCreator(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		paperTransitionRepository.findAll().forEach(paperTransition -> {
			if (paperTransition.getCreatedBy() != null) {
				paperTransition.setCreatorId(paperTransition.getCreatedBy().getId());
				paperTransitionRepository.save(paperTransition);
			}
		});
	}

	@Override
	public void updateMessageCategory(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		messageRepository.findAll().forEach(message -> {
			Paper paper = paperRepository.findById(message.getBizRefId());
			if (paper != null) {
				message.setCategory(paper.getCategory());
				messageRepository.save(message);
			}
		});
	}

	@Override
	public void fixNoticeReadCounter(User user) {

	}

}
