package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.model.SubContractHistory;
import cn.com.starest.nextoa.project.domain.request.SaveSubContractRequest;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 主合同以及分包合同有关服务
 *
 * @author dz
 */
public interface SubContractService {

	ModuleActions.ModuleAction[] resolveGrantedSubContractActions(User byWho);

	/**
	 * @param subContract
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(SubContract subContract, User byWho);

	SubContract createSubContract(SaveSubContractRequest request, User byWho);

	SubContract updateSubContract(String id, SaveSubContractRequest request, User byWho);

	SubContract publishSubContract(String id, User byWho);

	SubContract unpublishSubContract(String id, User byWho);

	void deleteSubContractById(String id, User byWho);

	SubContract findSubContractById(String id, User byWho);

	Page<SubContract> listSubContracts(SubContractQueryRequest request, User byWho);

	List<SubContract> listSubContractsByProject(String projectId, User byWho);

	List<SubContract> listSubContractsByProject(Project project, User byWho);

	Page<SubContractHistory> listSubContractHistories(String id, PageQueryRequest request);

	SubContractHistory findSubContractHistoryById(String id);

}
