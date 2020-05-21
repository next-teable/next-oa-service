package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.DictionarySummary;
import cn.com.starest.nextoa.project.web.dto.SaveDictionaryParameter;

import java.util.List;

/**
 * 数据字典维护服务（可新增,修改,不能删除）
 *
 * @author dz
 */
public interface DictionaryRestSupport {

	DictionarySummary createProjectType(SaveDictionaryParameter request, User byWho);

	DictionarySummary updateProjectType(String id, SaveDictionaryParameter request, User byWho);

	DictionarySummary findProjectTypeById(String id);

	List<DictionarySummary> listProjectTypes();

	DictionarySummary createProjectDeliveryType(SaveDictionaryParameter request, User byWho);

	DictionarySummary updateProjectDeliveryType(String id, SaveDictionaryParameter request, User byWho);

	DictionarySummary findProjectDeliveryTypeById(String id);

	List<DictionarySummary> listProjectDeliveryTypes();

	DictionarySummary createContractType(SaveDictionaryParameter request, User byWho);

	DictionarySummary updateContractType(String id, SaveDictionaryParameter request, User byWho);

	DictionarySummary findContractTypeById(String id);

	List<DictionarySummary> listContractTypes();

	DictionarySummary createDepositType(SaveDictionaryParameter request, User byWho);

	DictionarySummary updateDepositType(String id, SaveDictionaryParameter request, User byWho);

	DictionarySummary findDepositTypeById(String id);

	List<DictionarySummary> listDepositTypes();

}
