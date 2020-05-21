package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;

import java.util.List;

/**
 * @author dz
 */
public interface FinancialReportService {

    /**
     * @param company
     * @return
     */
    List<CompanyFinancialAggregation> calculateCompanyFinancialReport(Company company);

    /**
     * @param company
     * @param year
     * @return
     */
    CompanyFinancialAggregation calculateCompanyFinancialReport(Company company, FinancialYear year);

    /**
     * @param companyFinancialReportList
     * @return
     */
    CompanyFinancialAggregation sumCompanyFinancialReport(List<CompanyFinancialAggregation> companyFinancialReportList);

    /**
     * @param department
     * @return
     */
    List<BizDepartmentFinancialAggregation> calculateBizDepartmentFinancialReport(BizDepartment department);

    /**
     * @param department
     * @param year
     * @return
     */
    BizDepartmentFinancialAggregation calculateBizDepartmentFinancialReport(BizDepartment department,
                                                                            FinancialYear year);

    //	/**
    //	 * @param bizDepartmentFinancialAggregationList
    //	 * @return
    //	 */
    //	BizDepartmentFinancialAggregation sumBizDepartmentFinancialReport(List<BizDepartmentFinancialAggregation> bizDepartmentFinancialAggregationList);

    /**
     * @param project
     * @return
     */
    ProjectFinancialAggregation calculateProjectFinancialReport(Project project, User byWho);

    /**
     * @param project
     * @param byWho
     * @param Year
     * @return
     */
    ProjectFinancialAggregation calculateProjectFinancialReport(Project project, User byWho, int Year);

    /**
     * @param company
     * @param year    (all the month)
     * @return
     */
    List<CompanyCapitalAggregation> calculateCompanyCapitalReport(Company company, int year);

    /**
     * @param company
     * @param year
     * @param month
     * @return
     */
    CompanyCapitalAggregation calculateCompanyCapitalReport(Company company, int year, int month);

    /**
     * @param projectSettlement
     * @return
     */
    ProjectSettlementAggregation calculateProjectSettlementAggregation(ProjectSettlement projectSettlement);


    /**
     * 只计算指定用户（可能是user1,2,3,4,5,以第一个匹配的为准）的结算信息,并将该数据放在user1对应的位置上
     *
     * @param projectSettlement
     * @param byWho
     * @return
     */
    ProjectSettlementAggregation calculateProjectSettlementAggregationByOneUser(ProjectSettlement projectSettlement,
                                                                                User byWho);

    /**
     * @param queryParameter
     * @param user
     * @return
     */
    ProjectFinancialAggregation sumProjectFinancialReport(ProjectQueryRequest queryParameter, User user);
}
