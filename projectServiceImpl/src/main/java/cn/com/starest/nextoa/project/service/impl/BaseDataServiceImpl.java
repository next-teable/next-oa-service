package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.FrameworkContractQueryRequest;
import cn.com.starest.nextoa.project.domain.rule.*;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class BaseDataServiceImpl implements BaseDataService {

	@Autowired
	private SystemSettingService systemSettingService;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private BiddingAgentRepository biddingAgentRepository;

	@Autowired
	private FrameworkContractRepository frameworkContractRepository;

	@Autowired
	private SubContractorRepository subContractorRepository;

	@Autowired
	private FirstPartyRepository firstPartyRepository;

	@Autowired(required = false)
	private List<BiddingAgentReference> biddingAgentReferenceList;

	@Autowired(required = false)
	private List<FrameworkContractReference> frameworkContractReferenceList;

	@Autowired(required = false)
	private List<CompanyReference> companyReferenceList;

	@Autowired(required = false)
	private List<FirstPartyReference> firstPartyReferenceList;

	@Autowired(required = false)
	private List<SubContractorReference> subContractorReferenceList;

	@Override
	public Company createCompany(Company request, User byWho) {
		if (companyRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的公司名");
		}

		request.setId(null);
		request.setCreatedBy(byWho);
		request.setCreatedAt(new Date());
		request.setModifiedBy(byWho);
		request.setModifiedAt(new Date());

		return companyRepository.save(request);
	}

	@Override
	public Company updateCompany(String id, Company request, User byWho) {
		Company company = companyRepository.findById(id);
		if (company == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		Company matchedCompany = companyRepository.findFirstByName(request.getName());
		if (matchedCompany != null && !matchedCompany.getId().equals(id)) {
			throw new ValidationException("重复的公司名");
		}

		company.setName(request.getName());
		company.setShortName(request.getShortName());
		company.setAddress(request.getAddress());
		company.setLegalPerson(request.getLegalPerson());
		company.setRegisteredPlace(request.getRegisteredPlace());
		company.setRegisteredCapital(request.getRegisteredCapital());
		company.setSort(request.getSort());
		company.setDescription(request.getDescription());
		company.setModifiedBy(byWho);
		company.setModifiedAt(new Date());

		return companyRepository.save(company);
	}

	@Override
	public Company deleteCompanyById(String id, User byWho) {
		Company company = companyRepository.findById(id);
		if (company == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (companyReferenceList != null) {
			companyReferenceList.forEach(ref -> {
				if (ref.hasReference(company)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		companyRepository.delete(company);
		return company;
	}

	@Override
	public Company findCompanyById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return companyRepository.findById(id);
	}

	@Override
	public Page<Company> listCompanies(NamedPageQueryRequest request) {
		if (!StringUtils.isEmpty(request.getName())) {
			return companyRepository.findPageByNameLike(request.getName(),
														new PageRequest(request.getStart(),
																		request.getLimit(),
																		new Sort(new Sort.Order(Sort.Direction.ASC,
																								"sort"))));
		}
		return companyRepository.findAll(new PageRequest(request.getStart(),
														 request.getLimit(),
														 new Sort(new Sort.Order(Sort.Direction.ASC, "sort"))));
	}

	@Override
	public Page<Company> listSupervisedCompanies(NamedPageQueryRequest request, User byWho) {
		//if the user is global supervisors, the user can query all the projects
		boolean supervisor = User.ADMINISTRATOR.equals(byWho.getUsername());
		if (!supervisor) {
			supervisor = systemSettingService.isCompanySupervisor(byWho);
		}

		if (supervisor) {
			return listCompanies(request);
		}

		return new PageImpl<>(Collections.emptyList(), new PageRequest(request.getStart(), request.getLimit()), 0);
	}

	@Override
	public FirstParty createFirstParty(FirstParty request, User byWho) {
		if (firstPartyRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的甲方名称");
		}

		request.setId(null);
		request.setCreatedBy(byWho);
		request.setCreatedAt(new Date());
		request.setModifiedBy(byWho);
		request.setModifiedAt(new Date());

		return firstPartyRepository.save(request);
	}

	@Override
	public FirstParty updateFirstParty(String id, FirstParty request, User byWho) {
		FirstParty firstParty = firstPartyRepository.findById(id);
		if (firstParty == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		FirstParty matchedFirstParty = firstPartyRepository.findFirstByName(request.getName());
		if (matchedFirstParty != null && !matchedFirstParty.getId().equals(id)) {
			throw new ValidationException("重复的甲方名称");
		}

		firstParty.setName(request.getName());
		firstParty.setProvince(request.getProvince());
		firstParty.setCity(request.getCity());
		firstParty.setDescription(request.getDescription());
		firstParty.setModifiedBy(byWho);
		firstParty.setModifiedAt(new Date());

		return firstPartyRepository.save(firstParty);
	}

	@Override
	public FirstParty deleteFirstPartyById(String id, User byWho) {
		FirstParty firstParty = firstPartyRepository.findById(id);
		if (firstParty == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (firstPartyReferenceList != null) {
			firstPartyReferenceList.forEach(ref -> {
				if (ref.hasReference(firstParty)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		firstPartyRepository.delete(firstParty);
		return firstParty;
	}

	@Override
	public FirstParty findFirstPartyById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return firstPartyRepository.findById(id);
	}

	@Override
	public Page<FirstParty> listFirstParties(NamedPageQueryRequest request) {
		if (!StringUtils.isEmpty(request.getName())) {
			return firstPartyRepository.findPageByNameLike(request.getName(),
														   new PageRequest(request.getStart(), request.getLimit()));
		}
		return firstPartyRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public BiddingAgent createBiddingAgent(BiddingAgent request, User byWho) {
		if (biddingAgentRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的招标代理单位名称");
		}

		request.setId(null);
		request.setCreatedBy(byWho);
		request.setCreatedAt(new Date());
		request.setModifiedBy(byWho);
		request.setModifiedAt(new Date());

		return biddingAgentRepository.save(request);
	}

	@Override
	public BiddingAgent updateBiddingAgent(String id, BiddingAgent request, User byWho) {
		BiddingAgent biddingAgent = biddingAgentRepository.findById(id);
		if (biddingAgent == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		BiddingAgent matchedBiddingAgent = biddingAgentRepository.findFirstByName(request.getName());
		if (matchedBiddingAgent != null && !matchedBiddingAgent.getId().equals(id)) {
			throw new ValidationException("重复的招标代理单位名称");
		}

		biddingAgent.setName(request.getName());
		biddingAgent.setDescription(request.getDescription());
		biddingAgent.setModifiedBy(byWho);
		biddingAgent.setModifiedAt(new Date());

		return biddingAgentRepository.save(biddingAgent);
	}

	@Override
	public BiddingAgent deleteBiddingAgentById(String id, User byWho) {
		BiddingAgent biddingAgent = biddingAgentRepository.findById(id);
		if (biddingAgent == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (biddingAgentReferenceList != null) {
			biddingAgentReferenceList.forEach(ref -> {
				if (ref.hasReference(biddingAgent)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		biddingAgentRepository.delete(biddingAgent);
		return biddingAgent;
	}

	@Override
	public BiddingAgent findBiddingAgentById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return biddingAgentRepository.findById(id);
	}

	@Override
	public Page<BiddingAgent> listBiddingAgents(NamedPageQueryRequest request) {
		if (!StringUtils.isEmpty(request.getName())) {
			return biddingAgentRepository.findPageByNameLike(request.getName(),
															 new PageRequest(request.getStart(), request.getLimit()));
		}
		return biddingAgentRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public FrameworkContract createFrameworkContract(FrameworkContract request, User byWho) {
		if (frameworkContractRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的招标合同名称");
		}

		request.setId(null);
		request.setCreatedBy(byWho);
		request.setCreatedAt(new Date());
		request.setModifiedBy(byWho);
		request.setModifiedAt(new Date());

		return frameworkContractRepository.save(request);
	}

	@Override
	public FrameworkContract updateFrameworkContract(String id, FrameworkContract request, User byWho) {
		FrameworkContract frameworkContract = frameworkContractRepository.findById(id);
		if (frameworkContract == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		FrameworkContract matchedFrameworkContract = frameworkContractRepository.findFirstByName(request.getName());
		if (matchedFrameworkContract != null && !matchedFrameworkContract.getId().equals(id)) {
			throw new ValidationException("重复的招标代理单位名称");
		}

		frameworkContract.setName(request.getName());
		frameworkContract.setType(request.getType());
		frameworkContract.setDescription(request.getDescription());
		frameworkContract.setModifiedBy(byWho);
		frameworkContract.setModifiedAt(new Date());

		return frameworkContractRepository.save(frameworkContract);
	}

	@Override
	public FrameworkContract deleteFrameworkContractById(String id, User byWho) {
		FrameworkContract frameworkContract = frameworkContractRepository.findById(id);
		if (frameworkContract == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (frameworkContractReferenceList != null) {
			frameworkContractReferenceList.forEach(ref -> {
				if (ref.hasReference(frameworkContract)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		frameworkContractRepository.delete(frameworkContract);
		return frameworkContract;
	}

	@Override
	public FrameworkContract findFrameworkContractById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return frameworkContractRepository.findById(id);
	}

	@Override
	public Page<FrameworkContract> listFrameworkContracts(FrameworkContractQueryRequest request) {
		if (request.getType() != null && !StringUtils.isEmpty(request.getName())) {
			return frameworkContractRepository.findPageByTypeAndNameLike(request.getType(),
																		 request.getName(),
																		 new PageRequest(request.getStart(),
																						 request.getLimit()));
		}
		else if (request.getType() != null) {
			return frameworkContractRepository.findPageByType(request.getType(),
															  new PageRequest(request.getStart(), request.getLimit()));
		}
		else if (!StringUtils.isEmpty(request.getName())) {
			return frameworkContractRepository.findPageByNameLike(request.getName(),
																  new PageRequest(request.getStart(),
																				  request.getLimit()));
		}

		return frameworkContractRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public SubContractor createSubContractor(SubContractor request, User byWho) {
		if (subContractorRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的分包单位名称");
		}

		request.setId(null);
		request.setCreatedBy(byWho);
		request.setCreatedAt(new Date());
		request.setModifiedBy(byWho);
		request.setModifiedAt(new Date());

		return subContractorRepository.save(request);
	}

	@Override
	public SubContractor updateSubContractor(String id, SubContractor request, User byWho) {
		SubContractor subContractor = subContractorRepository.findById(id);
		if (subContractor == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		SubContractor matchedSubContractor = subContractorRepository.findFirstByName(request.getName());
		if (matchedSubContractor != null && !matchedSubContractor.getId().equals(id)) {
			throw new ValidationException("重复的分包名称");
		}

		subContractor.setName(request.getName());
		subContractor.setContactPerson(request.getContactPerson());
		subContractor.setContactPhone(request.getContactPhone());
		subContractor.setDescription(request.getDescription());
		subContractor.setModifiedBy(byWho);
		subContractor.setModifiedAt(new Date());

		return subContractorRepository.save(subContractor);
	}

	@Override
	public SubContractor deleteSubContractorById(String id, User byWho) {
		SubContractor subContractor = subContractorRepository.findById(id);
		if (subContractor == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (subContractorReferenceList != null) {
			subContractorReferenceList.forEach(ref -> {
				if (ref.hasReference(subContractor)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		subContractorRepository.delete(subContractor);
		return subContractor;
	}

	@Override
	public SubContractor findSubContractorById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return subContractorRepository.findById(id);
	}

	@Override
	public Page<SubContractor> listSubContractors(NamedPageQueryRequest request) {
		if (!StringUtils.isEmpty(request.getName())) {
			return subContractorRepository.findPageByNameLike(request.getName(),
															  new PageRequest(request.getStart(), request.getLimit()));
		}
		return subContractorRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

}
