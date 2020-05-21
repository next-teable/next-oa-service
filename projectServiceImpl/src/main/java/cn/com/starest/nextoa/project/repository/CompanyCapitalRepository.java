package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import cn.com.starest.nextoa.project.repository.custom.CompanyCapitalRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * CompanyCapitalRepository
 */
public interface CompanyCapitalRepository extends AbstractRepository<CompanyCapital>, CompanyCapitalRepositoryCustom {

	CompanyCapital findFirstByCompanyAndYearAndMonth(Company company, int year, int month);

}