package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserOpinion;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

public interface UserOpinionRepository extends AbstractRepository<UserOpinion> {

	List<UserOpinion> findListByCreatedByOrderByCreatedAtAsc(User user);

	long countByCreatedBy(User user);

}
