package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.dto.SaveProjectSettlementParameter;

import java.util.List;

/**
 * 预算管理
 *
 * @author dz
 */
public interface ProfitSettingRestSupport {

	void updateProjectProfitSetting(String id, SaveProjectSettlementParameter request, User user);

	ProjectSettlementSummary findProjectProfitSettingById(String id);

	List<ProjectSettlementSummary> listProjectProfitSettings();

}
