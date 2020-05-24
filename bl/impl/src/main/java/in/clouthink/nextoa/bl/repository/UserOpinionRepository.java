package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserOpinion;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.List;

public interface UserOpinionRepository extends AbstractRepository<UserOpinion> {

	List<UserOpinion> findListByCreatedByOrderByCreatedAtAsc(User user);

	long countByCreatedBy(User user);

}
