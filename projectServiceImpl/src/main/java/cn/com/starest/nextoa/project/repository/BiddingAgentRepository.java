package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.BiddingAgent;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface BiddingAgentRepository extends AbstractRepository<BiddingAgent> {

	Page<BiddingAgent> findPageByNameLike(String name, Pageable pageable);

	BiddingAgent findFirstByName(String name);

}