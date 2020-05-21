package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.*;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserContactGroupRelationshipRepository extends AbstractRepository<UserContactGroupRelationship> {

	Page<UserContactGroupRelationship> findByContactGroup(ContactGroup contactGroup, Pageable pageable);

	Page<UserContactGroupRelationship> findByUser(User user, Pageable pageable);

	int countByContactGroup(ContactGroup contactGroup);

	UserContactGroupRelationship findByContactGroupAndUser(ContactGroup contactGroup, User user);

	void deleteByUser(User user);

}
