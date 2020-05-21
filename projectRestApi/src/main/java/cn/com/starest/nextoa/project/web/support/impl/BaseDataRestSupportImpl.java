package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.openapi.dto.NamedPageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.BaseDataRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class BaseDataRestSupportImpl implements BaseDataRestSupport {

	@Autowired
	private BaseDataService baseDataService;

	@Override
	public CompanySummary createCompany(Company request, User byWho) {
		return CompanySummary.from(baseDataService.createCompany(request, byWho));
	}

	@Override
	public CompanySummary updateCompany(String id, Company request, User byWho) {
		return CompanySummary.from(baseDataService.updateCompany(id, request, byWho));
	}

	@Override
	public void deleteCompanyById(String id, User byWho) {
		baseDataService.deleteCompanyById(id, byWho);
	}

	@Override
	public CompanySummary findCompanyById(String id) {
		return CompanySummary.from(baseDataService.findCompanyById(id));
	}

	@Override
	public List<CompanySummary> listCompanies() {
		return baseDataService.listCompanies(new NamedPageQueryParameter(0, 100))
							  .getContent()
							  .stream()
							  .map(CompanySummary::from)
							  .collect(Collectors.toList());
	}

	@Override
	public FirstPartySummary createFirstParty(FirstParty request, User byWho) {
		return FirstPartySummary.from(baseDataService.createFirstParty(request, byWho));
	}

	@Override
	public FirstPartySummary updateFirstParty(String id, FirstParty request, User byWho) {
		return FirstPartySummary.from(baseDataService.updateFirstParty(id, request, byWho));
	}

	@Override
	public void deleteFirstPartyById(String id, User byWho) {
		baseDataService.deleteFirstPartyById(id, byWho);
	}

	@Override
	public FirstPartySummary findFirstPartyById(String id) {
		return FirstPartySummary.from(baseDataService.findFirstPartyById(id));
	}

	@Override
	public Page<FirstPartySummary> listFirstParties(NamedPageQueryRequest request) {
		Page<FirstParty> firstPartyPage = baseDataService.listFirstParties(request);

		return new PageImpl<>(firstPartyPage.getContent()
											.stream()
											.map(FirstPartySummary::from)
											.collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  firstPartyPage.getTotalElements());
	}

	@Override
	public BiddingAgentSummary createBiddingAgent(BiddingAgent request, User byWho) {
		return BiddingAgentSummary.from(baseDataService.createBiddingAgent(request, byWho));
	}

	@Override
	public BiddingAgentSummary updateBiddingAgent(String id, BiddingAgent request, User byWho) {
		return BiddingAgentSummary.from(baseDataService.updateBiddingAgent(id, request, byWho));
	}

	@Override
	public void deleteBiddingAgentById(String id, User byWho) {
		baseDataService.deleteBiddingAgentById(id, byWho);
	}

	@Override
	public BiddingAgentSummary findBiddingAgentById(String id) {
		return BiddingAgentSummary.from(baseDataService.findBiddingAgentById(id));
	}

	@Override
	public Page<BiddingAgentSummary> listBiddingAgents(NamedPageQueryRequest request) {
		Page<BiddingAgent> biddingAgentPage = baseDataService.listBiddingAgents(request);

		return new PageImpl<>(biddingAgentPage.getContent()
											  .stream()
											  .map(BiddingAgentSummary::from)
											  .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  biddingAgentPage.getTotalElements());
	}

	@Override
	public FrameworkContractSummary createFrameworkContract(FrameworkContract request, User byWho) {
		return FrameworkContractSummary.from(baseDataService.createFrameworkContract(request, byWho));
	}

	@Override
	public FrameworkContractSummary updateFrameworkContract(String id, FrameworkContract request, User byWho) {
		return FrameworkContractSummary.from(baseDataService.updateFrameworkContract(id, request, byWho));
	}

	@Override
	public void deleteFrameworkContractById(String id, User byWho) {
		baseDataService.deleteFrameworkContractById(id, byWho);
	}

	@Override
	public FrameworkContractSummary findFrameworkContractById(String id) {
		return FrameworkContractSummary.from(baseDataService.findFrameworkContractById(id));
	}

	@Override
	public Page<FrameworkContractSummary> listFrameworkContracts(FrameworkContractQueryParameter request) {
		Page<FrameworkContract> frameworkContractPage = baseDataService.listFrameworkContracts(request);

		return new PageImpl<>(frameworkContractPage.getContent()
												   .stream()
												   .map(FrameworkContractSummary::from)
												   .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  frameworkContractPage.getTotalElements());
	}

	@Override
	public SubContractorSummary createSubContractor(SubContractor request, User byWho) {
		return SubContractorSummary.from(baseDataService.createSubContractor(request, byWho));
	}

	@Override
	public SubContractorSummary updateSubContractor(String id, SubContractor request, User byWho) {
		return SubContractorSummary.from(baseDataService.updateSubContractor(id, request, byWho));
	}

	@Override
	public void deleteSubContractorById(String id, User byWho) {
		baseDataService.deleteSubContractorById(id, byWho);
	}

	@Override
	public SubContractorSummary findSubContractorById(String id) {
		return SubContractorSummary.from(baseDataService.findSubContractorById(id));
	}

	@Override
	public Page<SubContractorSummary> listSubContractors(NamedPageQueryRequest request) {
		Page<SubContractor> biddingAgentPage = baseDataService.listSubContractors(request);

		return new PageImpl<>(biddingAgentPage.getContent()
											  .stream()
											  .map(SubContractorSummary::from)
											  .collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  biddingAgentPage.getTotalElements());
	}

}
