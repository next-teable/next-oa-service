package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Salary;
import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import cn.com.starest.nextoa.project.domain.model.SalaryImportHistory;
import cn.com.starest.nextoa.project.domain.request.SalaryAggregationQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SalaryQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveSalaryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
public interface SalaryService {

	/**
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param item
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(Salary item, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Salary createSalary(SaveSalaryRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	Salary updateSalary(String id, SaveSalaryRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	Salary findSalaryById(String id, User byWho);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteSalaryById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<Salary> listSalaries(SalaryQueryRequest request, User byWho);

	/**
	 * @param id
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateBizDepartmentSalary(String id, int year, int month);

	/**
	 * @param id
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateProjectSalary(String id, int year, int month);

	/**
	 * @param requestList
	 * @param byWho
	 * @return
	 */
	SalaryImportHistory importSalaries(List<SaveSalaryRequest> requestList, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<SalaryImportHistory> listSalaryImportHistories(PageQueryParameter request, User byWho);

	/**
	 * @param request
	 * @param type
	 * @param byWho
	 * @return
	 */
	Page<SalaryAggregation> listSalaryByAggregation(SalaryAggregationQueryRequest request,
													SalaryAggregation.AggregationType type,
													User byWho);
}
