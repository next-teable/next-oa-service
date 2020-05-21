package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectCompletionQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectCompletionRequest;
import cn.com.starest.nextoa.project.domain.rule.ProjectCompletionReference;
import cn.com.starest.nextoa.project.exception.OrderException;
import cn.com.starest.nextoa.project.exception.ProjectCompletionException;
import cn.com.starest.nextoa.project.exception.ProjectCompletionNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectException;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectCompletionRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ProjectCompletionService;
import in.clouthink.daas.edm.Edms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author dz
 */
@Service
public class ProjectCompletionServiceImpl implements ProjectCompletionService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private ProjectCompletionRepository projectCompletionRepository;

	@Autowired(required = false)
	private List<ProjectCompletionReference> projectCompletionReferenceList;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.PROJECT_COMPLETION, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(ProjectCompletion projectCompletion, User byWho) {
		List<ModuleActions.ModuleAction> result = modulePermissionService.listGrantedActions(Module.PROJECT_COMPLETION,
																							 byWho);

		if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.PENDING_SETTLEMENT ||
			projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.SETTLEMENT_DONE) {
			result.remove(ModuleActions.EDIT_ACTION);
		}

		return result.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ProjectCompletion createProjectCompletion(SaveProjectCompletionRequest request, User byWho) {
		ProjectCompletion projectCompletion = new ProjectCompletion();

		handleProjectCompletionReference(request, projectCompletion);
		BeanUtils.copyProperties(request, projectCompletion);
		ProjectCompletion.onCreate(projectCompletion, byWho);

		projectCompletion = projectCompletionRepository.save(projectCompletion);

		if (projectCompletion.getOrder() != null) {
			Order order = projectCompletion.getOrder();
			order.setCompleted(true);
			order.setCompletedAt(projectCompletion.getCreatedAt());
			order.setCompletedBy(projectCompletion.getCreatedBy());
			orderRepository.save(order);

			Edms.getEdm("project").dispatch("completeByOrder", order);
		}

		if (projectCompletion.getContract() != null) {
			Contract contract = projectCompletion.getContract();
			contract.setCompleted(true);
			contract.setCompletedAt(projectCompletion.getCreatedAt());
			contract.setCompletedBy(projectCompletion.getCreatedBy());
			contractRepository.save(contract);
			Edms.getEdm("project").dispatch("completeByContract", contract);
		}

		return projectCompletion;
	}

	@Override
	public ProjectCompletion updateProjectCompletion(String id, SaveProjectCompletionRequest request, User byWho) {
		ProjectCompletion projectCompletion = projectCompletionRepository.findById(id);
		if (projectCompletion == null) {
			throw new ProjectCompletionNotFoundException("没有找到对应的竣工.");
		}

		if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.PENDING_SETTLEMENT) {
			throw new ProjectCompletionNotFoundException("不能修改待结算状态的竣工数据.");
		}

		if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.SETTLEMENT_DONE) {
			throw new ProjectCompletionNotFoundException("不能修改已结算状态的竣工数据.");
		}

		//不能修改项目,合同,订单等关键信息
		BeanUtils.copyProperties(request, projectCompletion);
		ProjectCompletion.onModify(projectCompletion, byWho);

		return projectCompletionRepository.save(projectCompletion);
	}

	@Override
	public void deleteProjectCompletionById(String id, User byWho) {
		ProjectCompletion projectCompletion = projectCompletionRepository.findById(id);
		if (projectCompletion == null) {
			throw new ProjectCompletionNotFoundException("没有找到对应的竣工.");
		}

		if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.PENDING_SETTLEMENT) {
			throw new ProjectCompletionNotFoundException("不能删除待结算状态的竣工数据.");
		}

		if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.SETTLEMENT_DONE) {
			throw new ProjectCompletionNotFoundException("不能删除已结算状态的竣工数据.");
		}

		if (projectCompletionReferenceList != null) {
			projectCompletionReferenceList.forEach(ref -> {
				if (ref.hasReference(projectCompletion)) {
					throw new OrderException("该数据已被其他业务引用,不能删除");
				}
			});
		}

		if (projectCompletion.getOrder() != null) {
			Order order = projectCompletion.getOrder();
			order.setCompleted(false);
			order.setCompletedAt(null);
			order.setCompletedBy(null);
			orderRepository.save(order);

			Edms.getEdm("project").dispatch("completeByOrder", order);
		}

		if (projectCompletion.getContract() != null) {
			Contract contract = projectCompletion.getContract();
			contract.setCompleted(false);
			contract.setCompletedAt(null);
			contract.setCompletedBy(null);
			contractRepository.save(contract);

			Edms.getEdm("project").dispatch("completeByContract", contract);
		}

		projectCompletionRepository.delete(projectCompletion);
	}

	@Override
	public ProjectCompletion findProjectCompletionById(String id, User byWho) {
		return projectCompletionRepository.findById(id);
	}

	@Override
	public Page<ProjectCompletion> listProjectCompletions(ProjectCompletionQueryRequest request, User byWho) {
		return projectCompletionRepository.queryPage(request);
	}

	@Override
	public List<ProjectCompletion> listProjectCompletionsByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		return listProjectCompletionsByProject(project, byWho);
	}

	@Override
	public List<ProjectCompletion> listProjectCompletionsByProject(Project project, User byWho) {
		if (project == null) {
			return Collections.emptyList();
		}
		return projectCompletionRepository.findListByProjectOrderByCreatedAtAsc(project);
	}

	private void handleProjectCompletionReference(SaveProjectCompletionRequest request,
												  ProjectCompletion projectCompletion) {
		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new ProjectCompletionException("无效的项目id");
		}
		projectCompletion.setProject(project);

		if (StringUtils.isEmpty(request.getContractId()) && StringUtils.isEmpty(request.getOrderId())) {
			throw new ProjectCompletionException("合同和订单不能同时为空");
		}

		if (!StringUtils.isEmpty(request.getContractId()) && !StringUtils.isEmpty(request.getOrderId())) {
			throw new ProjectCompletionException("不能同时选择合同和订单");
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			Contract contract = contractRepository.findById(request.getContractId());
			if (contract == null) {
				throw new ProjectCompletionException("无效的合同id");
			}

			if (contract.getProjects().stream().filter(proj -> proj.getId().equals(project.getId())).count() == 0) {
				throw new ProjectCompletionException("该合同和项目不匹配");
			}

			//check existed
			ProjectCompletion existProjectCompletion = projectCompletionRepository.findFirstByContract(contract);
			if (existProjectCompletion == null) {
				projectCompletion.setContract(contract);
			}
			else {
				if (StringUtils.isEmpty(projectCompletion.getId())) {
					throw new ProjectException("该合同已经有对应的竣工管理数据");
				}
				else if (!existProjectCompletion.getId().equals(projectCompletion.getId())) {
					throw new ProjectException("该合同已经有对应的竣工管理数据");
				}
				else {
					projectCompletion.setContract(contract);
				}
			}
		}
		else {
			projectCompletion.setContract(null);
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			Order order = orderRepository.findById(request.getOrderId());
			if (order == null) {
				throw new ProjectCompletionException("无效的订单id");
			}

			if (!order.getProject().getId().equals(project.getId())) {
				throw new ProjectCompletionException("该订单和项目不匹配");
			}

			//check existed
			ProjectCompletion existProjectCompletion = projectCompletionRepository.findFirstByOrder(order);
			if (existProjectCompletion == null) {
				projectCompletion.setOrder(order);
			}
			else {
				if (StringUtils.isEmpty(projectCompletion.getId())) {
					throw new ProjectException("该订单已经有对应的竣工管理数据");
				}
				else if (!existProjectCompletion.getId().equals(projectCompletion.getId())) {
					throw new ProjectException("该订单已经有对应的竣工管理数据");
				}
				else {
					projectCompletion.setOrder(order);
				}
			}
		}
		else {
			projectCompletion.setOrder(null);
		}
	}


}
