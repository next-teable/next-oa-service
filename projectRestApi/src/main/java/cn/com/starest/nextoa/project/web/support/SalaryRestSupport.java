package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.SaveSalaryRequest;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartRequest;

/**
 * @author dz
 */
public interface SalaryRestSupport {

	Page<SalaryAggregationSummary> listSalaryAggregationByYear(SalaryAggregationQueryParameter queryParameter, User user);

	Page<SalaryAggregationSummary> listSalaryAggregationByMonth(SalaryAggregationQueryParameter queryParameter, User user);

//	Page<SalaryAggregationSummary> listSalaryByAggregation(SalaryAggregationQueryParameter queryParameter, User user);

	SalaryImportHistorySummary importSalary(MultipartRequest multipartRequest, User user);

	Page<SalaryImportHistorySummary> listSalaryImportHistories(PageQueryParameter request, User byWho);

	SalarySummary createSalary(SaveSalaryRequest request, User byWho);

	SalarySummary updateSalary(String id, SaveSalaryRequest request, User byWho);

	SalaryDetail findSalaryById(String id, User byWho);

	Page<SalarySummary> listSalaries(SalaryQueryParameter request, User byWho);

	void deleteSalaryById(String id, User user);
}
