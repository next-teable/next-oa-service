package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.FrameworkContractQueryRequest;
import org.springframework.data.domain.Page;

/**
 * 基础数据维护服务
 *
 * @author dz
 */
public interface BaseDataService {

	//***************************
	//Company
	//***************************

	Company createCompany(Company request, User byWho);

	Company updateCompany(String id, Company request, User byWho);

	Company deleteCompanyById(String id, User byWho);

	Company findCompanyById(String id);

	Page<Company> listCompanies(NamedPageQueryRequest request);

	Page<Company> listSupervisedCompanies(NamedPageQueryRequest request, User byWho);

	//***************************
	//BiddingAgent
	//***************************

	BiddingAgent createBiddingAgent(BiddingAgent request, User byWho);

	BiddingAgent updateBiddingAgent(String id, BiddingAgent request, User byWho);

	BiddingAgent deleteBiddingAgentById(String id, User byWho);

	BiddingAgent findBiddingAgentById(String id);

	Page<BiddingAgent> listBiddingAgents(NamedPageQueryRequest request);

	//***************************
	//FrameworkContract
	//***************************

	FrameworkContract createFrameworkContract(FrameworkContract request, User byWho);

	FrameworkContract updateFrameworkContract(String id, FrameworkContract request, User byWho);

	FrameworkContract deleteFrameworkContractById(String id, User byWho);

	FrameworkContract findFrameworkContractById(String id);

	Page<FrameworkContract> listFrameworkContracts(FrameworkContractQueryRequest request);

	//***************************
	//FirstParty
	//***************************

	FirstParty createFirstParty(FirstParty request, User byWho);

	FirstParty updateFirstParty(String id, FirstParty request, User byWho);

	FirstParty deleteFirstPartyById(String id, User byWho);

	FirstParty findFirstPartyById(String id);

	Page<FirstParty> listFirstParties(NamedPageQueryRequest request);

	//***************************
	//SubContractor
	//***************************

	SubContractor createSubContractor(SubContractor request, User byWho);

	SubContractor updateSubContractor(String id, SubContractor request, User byWho);

	SubContractor deleteSubContractorById(String id, User byWho);

	SubContractor findSubContractorById(String id);

	Page<SubContractor> listSubContractors(NamedPageQueryRequest request);


}
