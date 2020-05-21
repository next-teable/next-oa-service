package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import cn.com.starest.nextoa.project.repository.custom.SalaryAggregationRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * SalaryAggregation repo
 */
public interface SalaryAggregationRepository extends AbstractRepository<SalaryAggregation>,
													 SalaryAggregationRepositoryCustom {

	SalaryAggregation findFirstByCompanyAndYearAndMonth(Company company, int year, int month);

}