package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.DepositQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveDepositRequest;
import cn.com.starest.nextoa.project.domain.rule.DepositReference;
import cn.com.starest.nextoa.project.exception.DepositException;
import cn.com.starest.nextoa.project.exception.DepositNotFoundException;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.DepositService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author dz
 */
@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private BiddingAgentRepository biddingAgentRepository;

	@Autowired
	private DepositTypeRepository depositTypeRepository;

	@Autowired(required = false)
	private List<DepositReference> depositReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private DepositRepository depositRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.DEPOSIT, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Deposit deposit, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.DEPOSIT, byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);

		return grantedActions;
	}

	@Override
	public Deposit createDeposit(SaveDepositRequest request, User byWho) {
		Deposit deposit = new Deposit();

		BeanUtils.copyProperties(request, deposit);
		handleDepositReference(request, deposit);
		Deposit.onCreate(deposit, byWho);

		return depositRepository.save(deposit);
	}

	@Override
	public Deposit updateDeposit(String id, SaveDepositRequest request, User byWho) {
		Deposit deposit = depositRepository.findById(id);
		if (deposit == null) {
			throw new DepositNotFoundException("没有找到对应的保证金.");
		}

		BeanUtils.copyProperties(request, deposit);
		handleDepositReference(request, deposit);
		Deposit.onModify(deposit, byWho);

		return depositRepository.save(deposit);
	}

	@Override
	public void deleteDepositById(String id, User byWho) {
		Deposit deposit = depositRepository.findById(id);
		if (deposit == null) {
			throw new DepositNotFoundException("没有找到对应的保证金.");
		}

		if (depositReferenceList != null) {
			depositReferenceList.forEach(ref -> {
				if (ref.hasReference(deposit)) {
					throw new DepositException("该保证金已被其他业务数据引用,不能删除.");
				}
			});
		}

		depositRepository.delete(deposit);
	}

	@Override
	public Deposit findDepositById(String id, User byWho) {
		return depositRepository.findById(id);
	}

	@Override
	public Page<Deposit> listDeposits(DepositQueryRequest request, User byWho) {
		return depositRepository.queryPage(request);
	}

	@Override
	public List<Deposit> listDepositsByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		return listDepositsByProject(project, byWho);
	}

	@Override
	public List<Deposit> listDepositsByProject(Project project, User byWho) {
		if (project == null) {
			return Collections.emptyList();
		}
		return depositRepository.findListByProjectOrderByCreatedAtAsc(project);
	}

	private void handleDepositReference(SaveDepositRequest request, Deposit deposit) {
		if (request.getReturnedAmount() != null &&
			request.getReturnedAmount().compareTo(BigDecimal.ZERO) > 0 &&
			request.getReturnedDate() == null) {
			throw new DepositException("请提供退回金额的日期.");
		}

		DepositType depositType = depositTypeRepository.findById(request.getDepositTypeId());
		if (depositType == null) {
			throw new EntityNotFoundException("无效的保证金类型id");
		}
		deposit.setType(depositType);

		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new EntityNotFoundException("无效的公司id");
		}
		deposit.setCompany(company);

		boolean isSetupProject = request.getSetupProject() != null ? request.getSetupProject().booleanValue() : false;

		if (isSetupProject) {
			if (StringUtils.isEmpty(request.getProjectId())) {
				throw new DepositException("请提供立项项目");
			}

			Project project = projectRepository.findById(request.getProjectId());
			if (project == null) {
				throw new EntityNotFoundException("无效的项目id");
			}
			deposit.setProject(project);
		}
		else {
			deposit.setProject(null);
		}

		if (!StringUtils.isEmpty(request.getBiddingAgentId())) {
			BiddingAgent biddingAgent = biddingAgentRepository.findById(request.getBiddingAgentId());
			if (biddingAgent == null) {
				throw new EntityNotFoundException("无效的招投标代理单位id");
			}
			deposit.setBiddingAgent(biddingAgent);
		}
		else {
			deposit.setBiddingAgent(null);
		}
	}

}
