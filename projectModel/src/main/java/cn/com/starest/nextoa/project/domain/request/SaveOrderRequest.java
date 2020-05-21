package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveOrderRequest {

	int getYear();

	//unique 订单编号
	String getCode();

	//unique 订单名称
	String getName();

	//项目名称（区域）*
	String getProjectId();

	//主合同/协议名称*
	String getContractId();

	BigDecimal getAmount();

	String getDescription();

}
