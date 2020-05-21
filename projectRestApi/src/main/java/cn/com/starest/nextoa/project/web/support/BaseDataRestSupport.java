package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 基础数据维护服务
 *
 * @author dz
 */
public interface BaseDataRestSupport {

	//***************************
	//Company
	//***************************

	CompanySummary createCompany(Company request, User byWho);

	CompanySummary updateCompany(String id, Company request, User byWho);

	void deleteCompanyById(String id, User byWho);

	CompanySummary findCompanyById(String id);

	List<CompanySummary> listCompanies();

	//***************************
	//FirstParty
	//***************************

	FirstPartySummary createFirstParty(FirstParty request, User byWho);

	FirstPartySummary updateFirstParty(String id, FirstParty request, User byWho);

	void deleteFirstPartyById(String id, User byWho);

	FirstPartySummary findFirstPartyById(String id);

	Page<FirstPartySummary> listFirstParties(NamedPageQueryRequest request);

	//***************************
	//BiddingAgent
	//***************************

	BiddingAgentSummary createBiddingAgent(BiddingAgent request, User byWho);

	BiddingAgentSummary updateBiddingAgent(String id, BiddingAgent request, User byWho);

	void deleteBiddingAgentById(String id, User byWho);

	BiddingAgentSummary findBiddingAgentById(String id);

	Page<BiddingAgentSummary> listBiddingAgents(NamedPageQueryRequest request);

	//***************************
	//FrameworkContract
	//***************************

	FrameworkContractSummary createFrameworkContract(FrameworkContract request, User byWho);

	FrameworkContractSummary updateFrameworkContract(String id, FrameworkContract request, User byWho);

	void deleteFrameworkContractById(String id, User byWho);

	FrameworkContractSummary findFrameworkContractById(String id);

	Page<FrameworkContractSummary> listFrameworkContracts(FrameworkContractQueryParameter request);

	//***************************
	//SubContractor
	//***************************

	SubContractorSummary createSubContractor(SubContractor request, User byWho);

	SubContractorSummary updateSubContractor(String id, SubContractor request, User byWho);

	void deleteSubContractorById(String id, User byWho);

	SubContractorSummary findSubContractorById(String id);

	Page<SubContractorSummary> listSubContractors(NamedPageQueryRequest request);

}
