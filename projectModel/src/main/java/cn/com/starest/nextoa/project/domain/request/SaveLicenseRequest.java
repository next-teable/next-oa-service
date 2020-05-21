package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveLicenseRequest {

	String getCode();

	//项目名称（区域）*
	String getProjectId();

	//主合同*
	String getContractId();

	//主订单*
	String getOrderId();

	BigDecimal getAmount();

	//外管证办理日期*
	Date getHandleDate();

	//	外管证有效期*
	Date getExpireDate();

	boolean isCancelled();

	//注销时间
	Date getCancelledAt();

	String getDescription();

}
