package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.ContractHistory;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveContractRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 主合同以及分包合同有关服务
 *
 * @author dz
 */
public interface ContractService {

	ModuleActions.ModuleAction[] resolveGrantedContractActions(User byWho);

	/**
	 * @param contract
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(Contract contract, User byWho);

	Contract createContract(SaveContractRequest request, User byWho);

	Contract updateContract(String id, SaveContractRequest request, User byWho);

	Contract publishContract(String id, User byWho);

	Contract unpublishContract(String id, User byWho);

	void deleteContractById(String id, User byWho);

	Contract findContractById(String id, User byWho);

	Page<Contract> listContracts(ContractQueryRequest request, User byWho);

	List<Contract> listContractsByProject(String projectId, User byWho);

	List<Contract> listContractsByProject(Project project, User byWho);

	Page<ContractHistory> listContractHistories(String id, PageQueryRequest request);

	ContractHistory findContractHistoryById(String id);

}
