package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveCompanyCapitalRequest {

	String getCompanyId();

	int getYear();

	int getMonth();

	BigDecimal getAmount();

	//排序
	String getSort();

	// 备注
	String getDescription();

}
