package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.SaveSubContractRequest;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import cn.com.starest.nextoa.project.web.dto.SubContractDetail;
import cn.com.starest.nextoa.project.web.dto.SubContractHistorySummary;
import cn.com.starest.nextoa.project.web.dto.SubContractSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface SubContractRestSupport {

	SubContractSummary createSubContract(SaveSubContractRequest request, User byWho);

	SubContractSummary updateSubContract(String id, SaveSubContractRequest request, User byWho);

	void publishSubContract(String id, User byWho);

	void unpublishSubContract(String id, User user);

	SubContractDetail findSubContractById(String id, User byWho);

	Page<SubContractSummary> listSubContracts(SubContractQueryRequest request, User byWho);

	void deleteSubContractById(String id, User user);

	Page<SubContractHistorySummary> listSubContractHistories(String id, PageQueryParameter request, User user);

	SubContractHistorySummary findSubContractHistoryById(String id, User user);

}
