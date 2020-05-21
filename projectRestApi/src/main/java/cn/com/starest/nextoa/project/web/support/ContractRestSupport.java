package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveContractRequest;
import cn.com.starest.nextoa.project.web.dto.ContractDetail;
import cn.com.starest.nextoa.project.web.dto.ContractHistorySummary;
import cn.com.starest.nextoa.project.web.dto.ContractSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ContractRestSupport {

	ContractSummary createContract(SaveContractRequest request, User byWho);

	ContractSummary updateContract(String id, SaveContractRequest request, User byWho);

	void publishContract(String id, User byWho);

	void unpublishContract(String id, User user);

	ContractDetail findContractById(String id, User byWho);

	Page<ContractSummary> listContracts(ContractQueryRequest request, User byWho);

	void deleteContractById(String id, User user);

	Page<ContractHistorySummary> listContractHistories(String id, PageQueryParameter request, User user);

	ContractHistorySummary findContractHistoryById(String id, User user);

}
