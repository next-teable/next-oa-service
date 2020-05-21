package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveSubContractRequest {

	//unique 主合同编号* 格式：分包单位简称-年月日-编号
	String getCode();

	//unique 主合同/协议名称*
	String getName();

	//项目名称（区域）*
	String getProjectId();

	//主合同/协议名称*
	String getContractId();

	//分包单位名称*
	String getSubContractorId();

	//分包比例
	BigDecimal getPercent();

	String getDescription();

}
