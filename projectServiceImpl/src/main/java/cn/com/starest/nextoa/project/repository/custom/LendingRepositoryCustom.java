package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.request.LendingQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface LendingRepositoryCustom {

	/**
	 * 动态查询
	 *
	 * @param request
	 * @return
	 */
	Page<Lending> queryPage(LendingQueryRequest request);

	/**
	 * 查询借款人累计借款多少
	 *
	 * @param lendingBy
	 * @return
	 */
	BigDecimal calculateLendingAmount(User lendingBy);

	/**
	 * 查询借款人累计还款多少
	 *
	 * @param lendingBy
	 * @return
	 */
	BigDecimal calculateRepaymentAmount(User lendingBy);

	/**
	 * 查询公司累计借款多少
	 *
	 * @param lendingTo
	 * @return
	 */
	BigDecimal calculateLendingAmount(Company lendingTo);

	/**
	 * 查询公司累计还款多少
	 *
	 * @param lendingTo
	 * @return
	 */
	BigDecimal calculateRepaymentAmount(Company lendingTo);

	/**
	 * 查询公司累计借款多少
	 *
	 * @param lendingFrom
	 * @return
	 */
	BigDecimal calculateLendingAmountByFrom(Company lendingFrom);

	BigDecimal calculateLendingAmountByFrom(Company lendingFrom, int year, int month);

	/**
	 * 查询公司累计借款多少
	 *
	 * @param lendingFrom
	 * @return
	 */
	BigDecimal calculateRepaymentAmountByFrom(Company lendingFrom);

	BigDecimal calculateRepaymentAmountByFrom(Company lendingFrom, int year, int month);

}
