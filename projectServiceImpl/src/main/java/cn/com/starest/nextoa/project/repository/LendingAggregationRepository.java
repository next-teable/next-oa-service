package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.repository.custom.LendingAggregationRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * LendingAggregation repo
 */
public interface LendingAggregationRepository extends AbstractRepository<LendingAggregation>,
													  LendingAggregationRepositoryCustom {

}