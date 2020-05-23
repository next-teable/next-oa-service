package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.RecentUserRelationship;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;

public interface RecentUserRelationshipRepository extends AbstractRepository<RecentUserRelationship> {

	List<RecentUserRelationship> findListByUserOrderByCreatedAtDesc(User user);

	void deleteByUser(User user);

}
