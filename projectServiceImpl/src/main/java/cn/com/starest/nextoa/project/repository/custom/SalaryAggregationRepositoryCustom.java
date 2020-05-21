package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import cn.com.starest.nextoa.project.domain.request.SalaryAggregationQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface SalaryAggregationRepositoryCustom {

	/**
	 * @param request
	 * @param aggregationType 按年或者按月
	 * @return
	 */
	Page<SalaryAggregation> queryPage(SalaryAggregationQueryRequest request,
									  SalaryAggregation.AggregationType aggregationType);

}
