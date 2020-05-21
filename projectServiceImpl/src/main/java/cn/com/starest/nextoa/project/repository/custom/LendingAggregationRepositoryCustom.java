package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface LendingAggregationRepositoryCustom {

	Page<LendingAggregation> queryPage(LendingAggregationQueryRequest request);

}
