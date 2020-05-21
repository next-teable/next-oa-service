package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 保存项目请求
 *
 * @author dz
 */
public interface SaveProjectRequest {

	String getProvince();

	String getCity();

	String getCompanyId();

	String getFrameworkContractId();

	String getName();

	String getCode();

	String getProjectTypeId();

	String getFirstPartyId();

	String[] getManagerIds();

	String[] getSupervisorIds();

	Date getPlanStart();

	Date getPlanEnd();

	Date getSetupDate();

	//产值利润率
	BigDecimal getOutputProfitRate();

	//回款利润率
	BigDecimal getCollectProfitRate();

	String getFirstPartyManager();

	String getFirstPartyPhone();

	String getDeliveryTypeId();

	String getDescription();

}
