package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.ProjectSettlementRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * ProjectProfitSettingRepository
 */
public interface ProjectSettlementRepository extends AbstractRepository<ProjectSettlement>,
													 ProjectSettlementRepositoryCustom {

	ProjectSettlement findFirstByProject(Project project);

	ProjectSettlement findFirstByOrder(Order order);

	ProjectSettlement findFirstByContract(Contract contract);

	ProjectSettlement findFirstByProjectCompletion(ProjectCompletion projectCompletion);

	List<ProjectSettlement> findListByProjectOrderByCreatedAtAsc(Project project);


}