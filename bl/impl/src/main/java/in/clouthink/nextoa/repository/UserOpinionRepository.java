package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserOpinion;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;

public interface UserOpinionRepository extends AbstractRepository<UserOpinion> {

	List<UserOpinion> findListByCreatedByOrderByCreatedAtAsc(User user);

	long countByCreatedBy(User user);

}
