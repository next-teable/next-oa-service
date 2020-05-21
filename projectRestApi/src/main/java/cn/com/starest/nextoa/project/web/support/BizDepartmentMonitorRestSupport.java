package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentFinancialAggregation;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentSummary;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.SalaryAggregationByMonth;
import cn.com.starest.nextoa.project.web.dto.SalarySummary;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface BizDepartmentMonitorRestSupport {

	Page<PaymentSummary> listBizDepartmentPayments(String id, int year, PageQueryParameter queryParameter, User user);

	Page<SalarySummary> listBizDepartmentSalaries(String id, int year, PageQueryParameter queryParameter, User user);

	BizDepartmentSummary findBizDepartmentById(String id, User user);

	BizDepartmentFinancialAggregation getBizDepartmentAggregation(String id, int year, User user);

	List<SalaryAggregationByMonth> listSalaryAggregationByMonth(String id,
																int year,
																PageQueryParameter queryParameter,
																User user);

	Page<SalarySummary> listBizDepartmentSalaries(String id, int year, int month, PageQueryParameter queryParameter, User user);
}
