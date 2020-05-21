package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.request.SaveAccountSubjectRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface AccountSubjectService {

	List<Module> listSupportedModules();

	AccountSubject createAccountSubject(SaveAccountSubjectRequest request, User byWho);

	AccountSubject addAccountSubjectChild(String id, SaveAccountSubjectRequest request, User byWho);

	AccountSubject updateAccountSubject(String id, SaveAccountSubjectRequest request, User byWho);

	AccountSubject deleteAccountSubjectById(String id, User byWho);

	AccountSubject findAccountSubjectById(String id);

	Page<AccountSubject> listAccountSubjects(PageQueryRequest request);

	Page<AccountSubject> listAccountSubjectChildren(String id, PageQueryRequest request);

	Page<AccountSubject> listAccountSubjectsByModule(Module module, PageQueryRequest request);

}
