package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.dto.SaveProjectSettlementParameter;
import cn.com.starest.nextoa.project.web.support.ProfitSettingRestSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dz
 */
@Component
public class ProfitSettingRestSupportImpl implements ProfitSettingRestSupport {

	@Override
	public void updateProjectProfitSetting(String id, SaveProjectSettlementParameter request, User user) {

	}

	@Override
	public ProjectSettlementSummary findProjectProfitSettingById(String id) {
		return null;
	}

	@Override
	public List<ProjectSettlementSummary> listProjectProfitSettings() {
		return null;
	}
}
