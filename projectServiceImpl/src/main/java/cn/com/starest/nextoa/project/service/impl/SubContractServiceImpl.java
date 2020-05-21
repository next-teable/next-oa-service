package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.SaveSubContractRequest;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import cn.com.starest.nextoa.project.domain.rule.SubContractReference;
import cn.com.starest.nextoa.project.exception.*;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.SubContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class SubContractServiceImpl implements SubContractService {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired
	private SubContractHistoryRepository subContractHistoryRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SubContractorRepository subContractorRepository;

	@Autowired(required = false)
	private List<SubContractReference> subContractReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedSubContractActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.SUBCONTRACT, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(SubContract subContract, User byWho) {
		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.SUBCONTRACT,
																									 byWho);
		if (subContract.isPublished()) {
			grantedActions.remove(ModuleActions.DELETE_ACTION);
			grantedActions.remove(ModuleActions.PUBLISH_ACTION);
		}
		else {
			grantedActions.remove(ModuleActions.UNPUBLISH_ACTION);
		}
		return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public SubContract createSubContract(SaveSubContractRequest request, User byWho) {
		checkDuplicateSubContract(request, null);

		SubContract subContract = new SubContract();
		BeanUtils.copyProperties(request, subContract);
		handleSubContractReference(request, subContract);
		SubContract.onCreate(subContract, byWho);

		return subContractRepository.save(subContract);
	}

	@Override
	public SubContract updateSubContract(String subContractId, SaveSubContractRequest request, User byWho) {
		SubContract subContract = subContractRepository.findById(subContractId);
		if (subContract == null) {
			throw new SubContractNotFoundException("没有找到对应的分包合同");
		}

		checkDuplicateSubContract(request, subContract);

		if (!subContract.isPublished()) {
			//草稿状态随便改
			return doUpdateSubContract(subContract, request, byWho);
		}
		else if (subContract.isPublished()) {
			//生效后每次修改要记录历史
			return doUpdateSubContractWithHistory(subContract, request, byWho);
		}
		else {
			throw new SubContractException("该分包合同已关闭,不能修改");
		}
	}

	private SubContract doUpdateSubContract(SubContract subContract, SaveSubContractRequest request, User byWho) {
		BeanUtils.copyProperties(request, subContract);
		handleSubContractReference(request, subContract);
		SubContract.onModify(subContract, byWho);

		return subContractRepository.save(subContract);
	}

	public SubContract doUpdateSubContractWithHistory(SubContract subContract,
													  SaveSubContractRequest request,
													  User byWho) {
		BeanUtils.copyProperties(request, subContract);
		handleSubContractReference(request, subContract);
		SubContract.onModify(subContract, byWho);

		SubContractHistory subContractHistory = new SubContractHistory();
		BeanUtils.copyProperties(subContract, subContractHistory, "id");
		subContractHistory.setSubContract(subContract);
		SubContractHistory.onModify(subContractHistory, byWho);

		subContractHistoryRepository.save(subContractHistory);
		return subContractRepository.save(subContract);
	}

	@Override
	public SubContract publishSubContract(String subContractId, User byWho) {
		SubContract existedSubContract = subContractRepository.findById(subContractId);
		if (existedSubContract == null) {
			throw new SubContractNotFoundException("没有找到对应的分包合同");
		}

		if (existedSubContract.isPublished()) {
			throw new SubContractException("该分包合同已生效,请勿重复操作");
		}

		existedSubContract.setPublished(true);
		existedSubContract.setPublishAt(new Date());
		existedSubContract.setPublishBy(byWho);

		SubContractHistory subContractHistory = new SubContractHistory();
		BeanUtils.copyProperties(existedSubContract, subContractHistory, "id");
		subContractHistory.setSubContract(existedSubContract);
		SubContractHistory.onModify(subContractHistory, byWho);
		subContractHistoryRepository.save(subContractHistory);

		return subContractRepository.save(existedSubContract);
	}

	@Override
	public SubContract unpublishSubContract(String subContractId, User byWho) {
		SubContract existedSubContract = subContractRepository.findById(subContractId);
		if (existedSubContract == null) {
			throw new SubContractNotFoundException("没有找到对应的分包合同");
		}

		if (!existedSubContract.isPublished()) {
			return existedSubContract;
		}

		existedSubContract.setPublished(false);
		existedSubContract.setPublishAt(null);
		existedSubContract.setPublishBy(null);

		return subContractRepository.save(existedSubContract);
	}

	@Override
	public void deleteSubContractById(String subContractId, User byWho) {
		SubContract subContract = subContractRepository.findById(subContractId);
		if (subContract == null) {
			throw new SubContractNotFoundException("没有找到对应的分包合同");
		}

		if (subContract.isPublished()) {
			throw new SubContractException("该分包合同已生效,不能删除");
		}

		if (subContractReferenceList != null) {
			subContractReferenceList.forEach(ref -> {
				if (ref.hasReference(subContract)) {
					throw new SubContractException("该分包合同已被其他业务数据引用,不能删除");
				}
			});
		}

		subContractHistoryRepository.deleteBySubContract(subContract);
		subContractRepository.delete(subContract);
	}

	@Override
	public SubContract findSubContractById(String subContractId, User byWho) {
		return subContractRepository.findById(subContractId);
	}

	@Override
	public Page<SubContract> listSubContracts(SubContractQueryRequest request, User byWho) {
		return subContractRepository.queryPage(request);
	}

	@Override
	public List<SubContract> listSubContractsByProject(String projectId, User byWho) {
		Project project = projectRepository.findById(projectId);
		if (project == null) {
			throw new ProjectNotFoundException("没有找到对应的项目");
		}

		return subContractRepository.findListByProject(project);
	}

	@Override
	public List<SubContract> listSubContractsByProject(Project project, User byWho) {
		return subContractRepository.findListByProject(project);
	}

	@Override
	public Page<SubContractHistory> listSubContractHistories(String id, PageQueryRequest request) {
		SubContract subContract = subContractRepository.findById(id);
		if (subContract == null) {
			throw new ContractNotFoundException("没有找到对应的分包合同");
		}

		return subContractHistoryRepository.findPageBySubContract(subContract,
																  new PageRequest(request.getStart(),
																				  request.getLimit(),
																				  new Sort(Sort.Direction.DESC,
																						   "modifiedAt")));
	}

	@Override
	public SubContractHistory findSubContractHistoryById(String id) {
		return subContractHistoryRepository.findById(id);
	}

	private void handleSubContractReference(SaveSubContractRequest request, SubContract subContract) {
		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new ProjectNotFoundException("没有找到对应的项目");
		}

		Contract contract = contractRepository.findById(request.getContractId());
		if (contract == null) {
			throw new ContractNotFoundException("没有找到对应的合同");
		}

		if (subContract.getContract() != null &&
			subContract.getContract().isSettled() &&
			!subContract.getContract().getId().equals(request.getContractId())) {
			throw new ContractException("该分包合同对应的合同已经结算,不能修改为其他合同");
		}
		else if (contract.isSettled()) {
			throw new ContractException("不能选择已经结算的合同");
		}

		SubContractor subContractor = subContractorRepository.findById(request.getSubContractorId());
		if (subContractor == null) {
			throw new EntityNotFoundException("没有找到对应的分包单位");
		}

		subContract.setProject(project);
		subContract.setContract(contract);
		subContract.setSubContractor(subContractor);
	}

	private void checkDuplicateSubContract(SaveSubContractRequest request, SubContract existedSubContract) {
		if (existedSubContract == null) {
			if (null != subContractRepository.findFirstByName(request.getName())) {
				throw new SubContractException("分包合同名称不能重复");
			}
			if (null != subContractRepository.findFirstByCode(request.getCode())) {
				throw new SubContractException("分包合同编码不能重复");
			}
		}
		else {
			SubContract matchedSubContract = subContractRepository.findFirstByName(request.getName());
			if (null != matchedSubContract && !matchedSubContract.getId().equals(existedSubContract.getId())) {
				throw new SubContractException("分包合同名称不能重复");
			}

			matchedSubContract = subContractRepository.findFirstByCode(request.getCode());
			if (null != matchedSubContract && !matchedSubContract.getId().equals(existedSubContract.getId())) {
				throw new SubContractException("分包合同编码不能重复");
			}
		}
	}

}
