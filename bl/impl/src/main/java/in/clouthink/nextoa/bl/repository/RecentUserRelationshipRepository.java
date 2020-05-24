package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.RecentUserRelationship;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.List;

public interface RecentUserRelationshipRepository extends AbstractRepository<RecentUserRelationship> {

	List<RecentUserRelationship> findListByUserOrderByCreatedAtDesc(User user);

	void deleteByUser(User user);

}
