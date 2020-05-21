package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.service.AccountSubjectService;
import cn.com.starest.nextoa.project.web.dto.AccountSubjectSummary;
import cn.com.starest.nextoa.project.web.dto.SaveAccountSubjectParameter;
import cn.com.starest.nextoa.project.web.support.AccountSubjectRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class AccountSubjectRestSupportImpl implements AccountSubjectRestSupport {

	@Autowired
	private AccountSubjectService accountSubjectService;

	@Override
	public AccountSubjectSummary createAccountSubject(SaveAccountSubjectParameter request, User byWho) {
		return AccountSubjectSummary.from(accountSubjectService.createAccountSubject(request, byWho));
	}

	@Override
	public AccountSubjectSummary updateAccountSubject(String id, SaveAccountSubjectParameter request, User byWho) {
		return AccountSubjectSummary.from(accountSubjectService.updateAccountSubject(id, request, byWho));
	}


	@Override
	public AccountSubjectSummary addAccountSubjectChild(String id, SaveAccountSubjectParameter request, User byWho) {
		return AccountSubjectSummary.from(accountSubjectService.addAccountSubjectChild(id, request, byWho));
	}

	@Override
	public void deleteAccountSubjectById(String id, User byWho) {
		accountSubjectService.deleteAccountSubjectById(id, byWho);
	}

	@Override
	public AccountSubjectSummary findAccountSubjectById(String id) {
		return AccountSubjectSummary.from(accountSubjectService.findAccountSubjectById(id));
	}

	@Override
	public List<AccountSubjectSummary> listAccountSubjects() {
		return accountSubjectService.listAccountSubjects(new PageQueryParameter(0, 100))
									.getContent()
									.stream()
									.map(AccountSubjectSummary::from)
									.collect(Collectors.toList());
	}

	@Override
	public List<AccountSubjectSummary> listAccountSubjectChildren(String id) {
		return accountSubjectService.listAccountSubjectChildren(id, new PageQueryParameter(0, 100))
									.getContent()
									.stream()
									.map(AccountSubjectSummary::from)
									.collect(Collectors.toList());
	}

	@Override
	public List<AccountSubjectSummary> listAccountSubjectsByModule(Module module) {
		return accountSubjectService.listAccountSubjectsByModule(module, new PageQueryParameter(0, 100))
									.getContent()
									.stream()
									.map(AccountSubjectSummary::from)
									.collect(Collectors.toList());
	}

	@Override
	public List<Module> listSupportedModules() {
		return accountSubjectService.listSupportedModules();
	}
}
