package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;
import cn.com.starest.nextoa.project.domain.request.LendingQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveLendingRequest;
import cn.com.starest.nextoa.project.domain.rule.LendingReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.LendingException;
import cn.com.starest.nextoa.project.exception.LendingNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.LendingService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class LendingServiceImpl implements LendingService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private SubContractorRepository subContractorRepository;

	@Autowired
	private LendingRepository lendingRepository;

	@Autowired
	private LendingAggregationRepository lendingAggregationRepository;

	@Autowired(required = false)
	private List<LendingReference> lendingReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.LENDING, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Lending lending, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.LENDING, byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);
		return grantedActions;
	}

	@Override
	public LendingAggregation findLendingAggregationById(String id, User byWho) {
		return lendingAggregationRepository.findById(id);
	}

	@Override
	public Page<LendingAggregation> listLendingAggregations(LendingAggregationQueryRequest request, User byWho) {
		return lendingAggregationRepository.queryPage(request);
	}

	@Override
	public Lending createLending(SaveLendingRequest request, User byWho) {
		Lending lending = new Lending();

		checkSaveLendingRequest(request);

		BeanUtils.copyProperties(request, lending);
		handleLendingReference(request, lending);

		if (request.getLendingType() == LendingType.LENDING) {
			lending.setAmount(request.getAmount().abs().negate());
		}
		else {
			lending.setAmount(request.getAmount().abs());
		}

		Lending.onCreate(lending, byWho);

		lending = lendingRepository.save(lending);
		updateLendingAggregation(lending);
		return lending;
	}

	@Override
	public Lending updateLending(String lendingId, SaveLendingRequest request, User byWho) {
		Lending lending = lendingRepository.findById(lendingId);
		if (lending == null) {
			throw new LendingNotFoundException("没有找到对应的借款");
		}

		Lending lendingBefore = new Lending();
		BeanUtils.copyProperties(lending, lendingBefore);

		checkSaveLendingRequest(request);

		BeanUtils.copyProperties(request, lending);
		handleLendingReference(request, lending);

		if (request.getLendingType() == LendingType.LENDING) {
			lending.setAmount(request.getAmount().abs().negate());
		}
		else {
			lending.setAmount(request.getAmount().abs());
		}

		Lending.onModify(lending, byWho);

		lending = lendingRepository.save(lending);
		updateLendingAggregation(lendingBefore);
		updateLendingAggregation(lending);
		return lending;
	}

	@Override
	public Lending findLendingById(String lendingId, User byWho) {
		return lendingRepository.findById(lendingId);
	}

	@Override
	public void deleteLendingById(String lendingId, User byWho) {
		Lending lending = lendingRepository.findById(lendingId);
		if (lending == null) {
			throw new LendingNotFoundException("没有找到对应的借款");
		}

		if (lendingReferenceList != null) {
			lendingReferenceList.forEach(ref -> {
				if (ref.hasReference(lending)) {
					throw new LendingException("该借款已被其他业务数据引用,不能删除");
				}
			});
		}

		lendingRepository.delete(lending);
		updateLendingAggregation(lending);
	}

	@Override
	public Page<Lending> listLending(LendingQueryRequest request, User byWho) {
		return lendingRepository.queryPage(request);
	}

	private void handleLendingReference(SaveLendingRequest request, Lending lending) {
		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new EntityNotFoundException("无效的公司id");
		}

		lending.setCompany(company);

		if (request.getLendingObject() == LendingObject.COMPANY) {
			if (!StringUtils.isEmpty(request.getLendingToId())) {
				Company lendingTo = companyRepository.findById(request.getLendingToId());
				if (lendingTo == null) {
					throw new EntityNotFoundException("无效的借款公司id");
				}
				lending.setLendingTo(lendingTo);
			}
			else {
				lending.setLendingTo(null);
			}

			lending.setLendingBy(null);
		}
		else if (request.getLendingObject() == LendingObject.PERSONAL) {
			if (!StringUtils.isEmpty(request.getLendingById())) {
				User user = accountService.findById(request.getLendingById());
				if (user == null) {
					throw new EntityNotFoundException("无效的借款用户id");
				}
				lending.setLendingBy(user);
			}
			else {
				lending.setLendingBy(null);
			}

			lending.setLendingTo(null);
		}
		else {
			lending.setLendingTo(null);
			lending.setLendingBy(null);
		}
	}

	private void checkSaveLendingRequest(SaveLendingRequest request) {
		if (request.getLendingObject() == LendingObject.COMPANY && StringUtils.isEmpty(request.getLendingToId())) {
			throw new LendingException("借（还）款对象为公司, 借款公司不能为空");
		}

		if (request.getLendingObject() == LendingObject.PERSONAL && StringUtils.isEmpty(request.getLendingById())) {
			throw new LendingException("借（还）款对象为个人, 借款用户不能为空");
		}
	}

	private void updateLendingAggregation(Lending lending) {
		LendingObject lendingObject = lending.getLendingObject();
		if (lendingObject == LendingObject.COMPANY && lending.getLendingTo() != null) {
			LendingAggregation lendingAggregation = lendingAggregationRepository.findById(lending.getLendingTo()
																								 .getId());
			if (lendingAggregation == null) {
				lendingAggregation = createLendingAggregation(lending.getLendingTo());
			}

			// do calculate （TODO: move to async mode）
			lendingAggregation.setLendingAmount(lendingRepository.calculateLendingAmount(lending.getLendingTo()));
			lendingAggregation.setRepaymentAmount(lendingRepository.calculateRepaymentAmount(lending.getLendingTo()));
			lendingAggregation.setOwedAmount(lendingAggregation.getLendingAmount()
															   .add(lendingAggregation.getRepaymentAmount()));
			lendingAggregationRepository.save(lendingAggregation);
		}
		else if (lendingObject == LendingObject.PERSONAL && lending.getLendingBy() != null) {
			LendingAggregation lendingAggregation = lendingAggregationRepository.findById(lending.getLendingBy()
																								 .getId());
			if (lendingAggregation == null) {
				lendingAggregation = createLendingAggregation(lending.getLendingBy());
			}
			// do calculate （TODO: move to async mode）
			lendingAggregation.setLendingAmount(lendingRepository.calculateLendingAmount(lending.getLendingBy()));
			lendingAggregation.setRepaymentAmount(lendingRepository.calculateRepaymentAmount(lending.getLendingBy()));
			lendingAggregation.setOwedAmount(lendingAggregation.getLendingAmount()
															   .add(lendingAggregation.getRepaymentAmount()));
			lendingAggregationRepository.save(lendingAggregation);
		}
	}

	private synchronized LendingAggregation createLendingAggregation(User user) {
		if (user == null) {
			return null;
		}
		LendingAggregation lendingAggregation = lendingAggregationRepository.findById(user.getId());
		if (lendingAggregation != null) {
			return lendingAggregation;
		}
		lendingAggregation = new LendingAggregation();
		lendingAggregation.setId(user.getId());
		lendingAggregation.setLendingObject(LendingObject.PERSONAL);
		lendingAggregation.setLendingBy(user);
		lendingAggregation.setLendingAmount(BigDecimal.ZERO);
		lendingAggregation.setRepaymentAmount(BigDecimal.ZERO);
		lendingAggregation.setOwedAmount(BigDecimal.ZERO);
		lendingAggregation.setModifiedAt(new Date());
		return lendingAggregationRepository.save(lendingAggregation);
	}

	private synchronized LendingAggregation createLendingAggregation(Company lendingTo) {
		if (lendingTo == null) {
			return null;
		}
		LendingAggregation lendingAggregation = lendingAggregationRepository.findById(lendingTo.getId());
		if (lendingAggregation != null) {
			return lendingAggregation;
		}
		lendingAggregation = new LendingAggregation();
		lendingAggregation.setId(lendingTo.getId());
		lendingAggregation.setLendingObject(LendingObject.COMPANY);
		lendingAggregation.setLendingTo(lendingTo);
		lendingAggregation.setLendingAmount(BigDecimal.ZERO);
		lendingAggregation.setRepaymentAmount(BigDecimal.ZERO);
		lendingAggregation.setOwedAmount(BigDecimal.ZERO);
		lendingAggregation.setModifiedAt(new Date());
		return lendingAggregationRepository.save(lendingAggregation);
	}
}
