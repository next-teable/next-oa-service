package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.DepositQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface DepositRepositoryCustom {

	Page<Deposit> queryPage(DepositQueryRequest request);

	BigDecimal calculateTotalTransferAmount(Company company);

	BigDecimal calculateTotalTransferAmount(Company company, int year, int month);

	BigDecimal calculateTotalTransferAmount(Project project);

	BigDecimal calculateTotalReturnedAmount(Company company);

	BigDecimal calculateTotalReturnedAmount(Company company, int year, int month);

	BigDecimal calculateTotalReturnedAmount(Project project);
}
