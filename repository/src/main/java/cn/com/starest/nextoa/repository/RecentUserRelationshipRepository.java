package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.RecentUserRelationship;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

public interface RecentUserRelationshipRepository extends AbstractRepository<RecentUserRelationship> {

	List<RecentUserRelationship> findListByUserOrderByCreatedAtDesc(User user);

	void deleteByUser(User user);

}
