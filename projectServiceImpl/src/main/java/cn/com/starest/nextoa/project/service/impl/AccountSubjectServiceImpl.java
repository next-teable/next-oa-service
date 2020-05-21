package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.request.SaveAccountSubjectRequest;
import cn.com.starest.nextoa.project.domain.rule.AccountSubjectReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.AccountSubjectRepository;
import cn.com.starest.nextoa.project.service.AccountSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author dz
 */
@Service
public class AccountSubjectServiceImpl implements AccountSubjectService {

	private static final List<Module> SUPPORTED_MODULES = Arrays.asList(new Module[]{Module.PAYMENT,
																					 Module.PENDING_PAYMENT,
																					 Module.REIMBURSE,
																					 Module.LENDING,
																					 Module.PROJECT_RECEIVED_PAYMENT,
																					 Module.COMPANY_RECEIVED_PAYMENT});

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	@Autowired(required = false)
	private List<AccountSubjectReference> accountSubjectReferenceList;

	@Override
	public AccountSubject createAccountSubject(SaveAccountSubjectRequest request, User byWho) {
		if (accountSubjectRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的科目名称");
		}

		AccountSubject accountSubject = new AccountSubject();

		BeanUtils.copyProperties(request, accountSubject);
		AccountSubject.onCreate(accountSubject, byWho);

		return accountSubjectRepository.save(accountSubject);
	}

	@Override
	public AccountSubject addAccountSubjectChild(String id, SaveAccountSubjectRequest request, User byWho) {
		AccountSubject parent = accountSubjectRepository.findById(id);
		if (parent == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		AccountSubject child = new AccountSubject();

		BeanUtils.copyProperties(request, child);
		child.setParent(parent);
		AccountSubject.onCreate(child, byWho);

		return accountSubjectRepository.save(child);
	}

	@Override
	public AccountSubject updateAccountSubject(String id, SaveAccountSubjectRequest request, User byWho) {
		AccountSubject accountSubject = accountSubjectRepository.findById(id);
		if (accountSubject == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		AccountSubject matchedAccountSubject = accountSubjectRepository.findFirstByName(request.getName());
		if (matchedAccountSubject != null && !matchedAccountSubject.getId().equals(id)) {
			throw new ValidationException("重复的科目名称");
		}

		BeanUtils.copyProperties(request, accountSubject);
		AccountSubject.onModify(accountSubject, byWho);

		return accountSubjectRepository.save(accountSubject);
	}

	@Override
	public AccountSubject deleteAccountSubjectById(String id, User byWho) {
		AccountSubject accountSubject = accountSubjectRepository.findById(id);
		if (accountSubject == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		if (accountSubjectReferenceList != null) {
			accountSubjectReferenceList.forEach(ref -> {
				if (ref.hasReference(accountSubject)) {
					throw new ValidationException("该数据已经被其他数据引用,不能删除");
				}
			});
		}
		accountSubjectRepository.delete(accountSubject);
		return accountSubject;
	}

	@Override
	public AccountSubject findAccountSubjectById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return accountSubjectRepository.findById(id);
	}

	@Override
	public Page<AccountSubject> listAccountSubjects(PageQueryRequest request) {
		return accountSubjectRepository.findPageByParent(null,
														 new PageRequest(request.getStart(),
																		 request.getLimit(),
																		 new Sort(new Sort.Order(Sort.Direction.ASC,
																								 "sort"))));
	}

	@Override
	public Page<AccountSubject> listAccountSubjectChildren(String id, PageQueryRequest request) {
		AccountSubject accountSubject = accountSubjectRepository.findById(id);
		if (accountSubject == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}
		return accountSubjectRepository.findPageByParent(accountSubject,
														 new PageRequest(request.getStart(),
																		 request.getLimit(),
																		 new Sort(new Sort.Order(Sort.Direction.ASC,
																								 "sort"))));
	}

	@Override
	public Page<AccountSubject> listAccountSubjectsByModule(Module module, PageQueryRequest request) {
		return accountSubjectRepository.findPageByParentIsNullAndModulesIn(module,
																		   new PageRequest(request.getStart(),
																						   request.getLimit(),
																						   new Sort(new Sort.Order(Sort.Direction.ASC,
																												   "sort"))));
	}

	@Override
	public List<Module> listSupportedModules() {
		return SUPPORTED_MODULES;
	}

}
