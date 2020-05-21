package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveDepositRequest {

	// 是否已立项目
	Boolean getSetupProject();

	// 保证金类型
	String getDepositTypeId();

	// 招标代理单位
	String getBiddingAgentId();

	// 公司
	String getCompanyId();

	// 已立项目（区域）*
	String getProjectId();

	// 未立项目 项目名称
	String getProjectName();

	// 保证金金额*
	BigDecimal getAmount();

	// 转账日期*
	Date getTransferDate();

	// 转账单位*
	String getTransferTo();

	// 经办人
	String getTransferOperator();

	// 退回日期
	Date getReturnedDate();

	// 退回金额
	BigDecimal getReturnedAmount();

	// 备注
	String getDescription();

}
