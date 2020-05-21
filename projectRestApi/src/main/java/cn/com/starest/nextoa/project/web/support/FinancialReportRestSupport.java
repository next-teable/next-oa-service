package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.web.dto.ProjectQueryParameter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The financial report
 *
 * @author dz
 */
public interface FinancialReportRestSupport {

	/**
	 * @param user
	 * @return company financial report
	 */
	List<CompanyFinancialReport> getCompanyFinancialReport(User user);

	/**
	 * @param user
	 * @return biz department financial report
	 */
	List<BizDepartmentFinancialReport> getBizDepartmentFinancialReport(User user);

	/**
	 * @param queryParameter
	 * @param user
	 * @return project financial report
	 */
	Page<ProjectFinancialAggregation> getProjectFinancialReport(ProjectQueryParameter queryParameter, User user);

	/**
	 * @param companyId
	 * @param year
	 * @param user
	 * @return
	 */
	CompanyCapitalReport getCompanyCapitalReport(String companyId, int year, User user);

	/**
	 *
	 * @param id
	 * @param user
     * @return
     */
    List<BusinessBizDepartmentFinancialAggregation> getBusinessBizDepartmentFinancialReport(String id, User user);
}
