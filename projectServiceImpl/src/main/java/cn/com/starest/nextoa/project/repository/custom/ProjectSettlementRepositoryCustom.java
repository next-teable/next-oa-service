package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.request.ProjectSettlementQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ProjectSettlementRepositoryCustom {

	Page<ProjectSettlement> queryPage(ProjectSettlementQueryRequest request);

	Page<ProjectSettlement> queryPage(ProjectSettlementQueryRequest request, User byWho);

}
