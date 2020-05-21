package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.CompanyCapitalQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyCapitalRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface CompanyCapitalService {

	/**
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param deposit
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(CompanyCapital deposit, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	CompanyCapital createCompanyCapital(SaveCompanyCapitalRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	CompanyCapital updateCompanyCapital(String id, SaveCompanyCapitalRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteCompanyCapitalById(String id, User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	CompanyCapital findCompanyCapitalById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<CompanyCapital> listCompanyCapitals(CompanyCapitalQueryRequest request, User byWho);

	/**
	 * 自动计算公司的每个月的初始现金
	 *
	 * @param company
	 */
	void autoCalculateInitialCapital(Company company);

//	/**
	//	 * 自动计算指定年份的每个月的初始现金
	//	 *
	//	 * @param company
	//	 * @param year
	//	 */
	//	void autoCalculateInitialCapital(Company company, int year);
	//
	//	/**
	//	 * 自动计算指定年份指定月份的初始现金
	//	 *
	//	 * @param company
	//	 * @param year
	//	 * @param month
	//	 */
	//	void autoCalculateInitialCapitalByMonth(Company company, int year, int month);

}
