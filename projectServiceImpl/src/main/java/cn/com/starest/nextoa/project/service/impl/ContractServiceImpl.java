package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveContractRequest;
import cn.com.starest.nextoa.project.domain.rule.ContractReference;
import cn.com.starest.nextoa.project.exception.ContractException;
import cn.com.starest.nextoa.project.exception.ContractNotFoundException;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectNotFoundException;
import cn.com.starest.nextoa.project.repository.ContractHistoryRepository;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.FirstPartyRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.ContractService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private ContractHistoryRepository contractHistoryRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private FirstPartyRepository firstPartyRepository;

	@Autowired(required = false)
	private List<ContractReference> contractReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	//############################
	// contract
	//############################

	public ModuleActions.ModuleAction[] resolveGrantedContractActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.CONTRACT, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Contract contract, User byWho) {
		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.CONTRACT,
																									 byWho);
		if (contract.isPublished()) {
			grantedActions.remove(ModuleActions.DELETE_ACTION);
			grantedActions.remove(ModuleActions.PUBLISH_ACTION);
		}
		else {
			grantedActions.remove(ModuleActions.UNPUBLISH_ACTION);
		}
		return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public Contract createContract(SaveContractRequest request, User byWho) {
		checkDuplicateContract(request, null);

		Contract contract = new Contract();
		BeanUtils.copyProperties(request, contract);
		handleContractReference(request, contract);
		Contract.onCreate(contract, byWho);

		return contractRepository.save(contract);
	}

	@Override
	public Contract updateContract(String contractId, SaveContractRequest request, User byWho) {
		Contract contract = contractRepository.findById(contractId);
		if (contract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		if (contract.isSettled()) {
			throw new ContractException("该合同已结算,不能修改");
		}

		checkDuplicateContract(request, contract);

		if (!contract.isPublished()) {
			//草稿状态随便改
			return doUpdateContract(contract, request, byWho);
		}
		else if (contract.isPublished()) {
			//生效后每次修改要记录历史
			return doUpdateContractWithHistory(contract, request, byWho);
		}
		else {
			throw new ContractException("该合同已关闭,不能修改");
		}
	}

	private Contract doUpdateContract(Contract contract, SaveContractRequest request, User byWho) {
		BeanUtils.copyProperties(request, contract);
		handleContractReference(request, contract);
		Contract.onModify(contract, byWho);

		return contractRepository.save(contract);
	}

	public Contract doUpdateContractWithHistory(Contract contract, SaveContractRequest request, User byWho) {
		BeanUtils.copyProperties(request, contract);
		handleContractReference(request, contract);
		Contract.onModify(contract, byWho);

		ContractHistory contractHistory = new ContractHistory();
		BeanUtils.copyProperties(contract, contractHistory, "id");
		contractHistory.setContract(contract);
		ContractHistory.onModify(contractHistory, byWho);

		contractHistoryRepository.save(contractHistory);
		return contractRepository.save(contract);
	}

	@Override
	public Contract publishContract(String contractId, User byWho) {
		Contract existedContract = contractRepository.findById(contractId);
		if (existedContract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		if (existedContract.isPublished()) {
			throw new ContractException("该合同已生效,请勿重复操作");
		}

		existedContract.setPublished(true);
		existedContract.setPublishAt(new Date());
		existedContract.setPublishBy(byWho);

		ContractHistory contractHistory = new ContractHistory();
		BeanUtils.copyProperties(existedContract, contractHistory, "id");
		contractHistory.setContract(existedContract);
		ContractHistory.onModify(contractHistory, byWho);
		contractHistoryRepository.save(contractHistory);

		return contractRepository.save(existedContract);
	}

	@Override
	public Contract unpublishContract(String contractId, User byWho) {
		Contract existedContract = contractRepository.findById(contractId);
		if (existedContract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		if (!existedContract.isPublished()) {
			return existedContract;
		}

		if (existedContract.isSettled()) {
			throw new ContractException("该合同已结算,不能取消发布");
		}

		existedContract.setPublished(false);
		existedContract.setPublishAt(null);
		existedContract.setPublishBy(null);

		return contractRepository.save(existedContract);
	}

	@Override
	public void deleteContractById(String contractId, User byWho) {
		Contract contract = contractRepository.findById(contractId);
		if (contract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		if (contract.isPublished()) {
			throw new ContractException("该合同已生效,不能删除");
		}

		if (contract.isSettled()) {
			throw new ContractException("该合同已结算,不能删除");
		}

		if (contractReferenceList != null) {
			contractReferenceList.forEach(ref -> {
				if (ref.hasReference(contract)) {
					throw new ContractException("该合同已被其他业务数据引用,不能删除");
				}
			});
		}

		contractHistoryRepository.deleteByContract(contract);
		contractRepository.delete(contract);
	}

	@Override
	public Contract findContractById(String contractId, User byWho) {
		return contractRepository.findById(contractId);
	}

	@Override
	public Page<Contract> listContracts(ContractQueryRequest request, User byWho) {
		return contractRepository.queryPage(request);
	}

	@Override
	public List<Contract> listContractsByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		if (project == null) {
			throw new ProjectNotFoundException("没有找到对应的项目");
		}

		return contractRepository.findListByProjectsIn(project);
	}

	@Override
	public List<Contract> listContractsByProject(Project project, User byWho) {
		return contractRepository.findListByProjectsIn(project);
	}

	@Override
	public Page<ContractHistory> listContractHistories(String contractId, PageQueryRequest pageQueryRequest) {
		Contract contract = contractRepository.findById(contractId);
		if (contract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		return contractHistoryRepository.findPageByContract(contract,
															new PageRequest(pageQueryRequest.getStart(),
																			pageQueryRequest.getLimit(),
																			new Sort(Sort.Direction.DESC,
																					 "modifiedAt")));
	}

	@Override
	public ContractHistory findContractHistoryById(String id) {
		return contractHistoryRepository.findById(id);
	}

	private void handleContractReference(SaveContractRequest request, Contract contract) {
		List<Project> projectList = new ArrayList<>();
		for (String projectId : request.getProjectIds()) {
			Project project = projectRepository.findById(projectId);
			if (project == null) {
				throw new ProjectNotFoundException("无效的项目id");
			}
			projectList.add(project);
		}


		FirstParty firstParty = firstPartyRepository.findById(request.getFirstPartyId());
		if (firstParty == null) {
			throw new EntityNotFoundException("无效的甲方id");
		}

		contract.setProjects(projectList);
		contract.setFirstParty(firstParty);
	}


	private void checkDuplicateContract(SaveContractRequest request, Contract existedContract) {
		if (existedContract == null) {
			if (null != contractRepository.findFirstByName(request.getName())) {
				throw new ContractException("合同名称不能重复");
			}
			if (null != contractRepository.findFirstByCode(request.getCode())) {
				throw new ContractException("合同编码不能重复");
			}
		}
		else {
			Contract matchedContract = contractRepository.findFirstByName(request.getName());
			if (null != matchedContract && !matchedContract.getId().equals(existedContract.getId())) {
				throw new ContractException("合同名称不能重复");
			}

			matchedContract = contractRepository.findFirstByCode(request.getCode());
			if (null != matchedContract && !matchedContract.getId().equals(existedContract.getId())) {
				throw new ContractException("合同编码不能重复");
			}
		}
	}

}
