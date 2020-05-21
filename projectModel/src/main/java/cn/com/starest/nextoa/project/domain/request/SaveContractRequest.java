package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveContractRequest {

	//unique 主合同编号* 格式：分包单位简称-年月日-编号
	String getCode();

	//unique 主合同/协议名称*
	String getName();

	//项目名称（区域）*
	String[] getProjectIds();

	//主合同签订甲方名称*
	String getFirstPartyId();

	BigDecimal getAmount();

	String getFirstPartyManager();

	String getFirstPartyPhone();

	String getDescription();

}
