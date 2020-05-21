package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlementAggregation;
import cn.com.starest.nextoa.project.domain.request.ProjectSettlementQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectSettlementRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ProjectSettlementService {

	/**
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param projectSettlement
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(ProjectSettlement projectSettlement, User byWho);

	/**
	 * @param projectCompletion
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(ProjectCompletion projectCompletion, User byWho);

	/**
	 * @param id      the id of ProjectCompletion
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectSettlement createProjectSettlement(String id, SaveProjectSettlementRequest request, User byWho);

	/**
	 * @param id      the id of ProjectSettlement
	 * @param request
	 * @param byWho
	 * @return
	 */
	ProjectSettlement updateProjectSettlement(String id, SaveProjectSettlementRequest request, User byWho);

	/**
	 * @param id    the id of ProjectSettlement
	 * @param byWho
	 * @return
	 */
	ProjectSettlement findProjectSettlementById(String id, User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deleteProjectSettlementById(String id, User user);

	/**
	 * @param id
	 * @param user
	 */
	void doProjectSettlement(String id, User user);

	/**
	 * @param request
	 * @return
	 */
	Page<ProjectSettlement> listProjectSettlements(ProjectSettlementQueryRequest request);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<ProjectSettlement> listProjectSettlements(ProjectSettlementQueryRequest request, User byWho);

	/**
	 *
	 * @param request
	 * @param byWho
     * @return
     */
	ProjectSettlementAggregation calculateProjectSettlementAggregation(ProjectSettlementQueryRequest request, User byWho);
}
