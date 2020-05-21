package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ContractType;
import cn.com.starest.nextoa.project.domain.model.DepositType;
import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.project.domain.model.ProjectType;
import cn.com.starest.nextoa.project.domain.request.SaveDictionaryRequest;
import org.springframework.data.domain.Page;

/**
 * 数据字典维护服务（可新增,修改,不能删除）
 *
 * @author dz
 */
public interface DictionaryService {

	//***************************
	//ProjectType
	//***************************

	ProjectType createProjectType(SaveDictionaryRequest request, User byWho);

	ProjectType updateProjectType(String id, SaveDictionaryRequest request, User byWho);

	ProjectType findProjectTypeById(String id);

	Page<ProjectType> listProjectTypes(PageQueryRequest request);

	//***************************
	//ProjectDeliveryType
	//***************************

	ProjectDeliveryType createProjectDeliveryType(SaveDictionaryRequest request, User byWho);

	ProjectDeliveryType updateProjectDeliveryType(String id, SaveDictionaryRequest request, User byWho);

	ProjectDeliveryType findProjectDeliveryTypeById(String id);

	Page<ProjectDeliveryType> listProjectDeliveryTypes(PageQueryRequest request);

	//***************************
	//ContractType
	//***************************

	ContractType createContractType(SaveDictionaryRequest request, User byWho);

	ContractType updateContractType(String id, SaveDictionaryRequest request, User byWho);

	ContractType findContractTypeById(String id);

	Page<ContractType> listContractTypes(PageQueryRequest request);

	//***************************
	//DepositType
	//***************************

	DepositType createDepositType(SaveDictionaryRequest request, User byWho);

	DepositType updateDepositType(String id, SaveDictionaryRequest request, User byWho);

	DepositType findDepositTypeById(String id);

	Page<DepositType> listDepositTypes(PageQueryRequest request);

}
