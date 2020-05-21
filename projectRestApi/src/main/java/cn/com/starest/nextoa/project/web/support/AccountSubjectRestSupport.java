package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.web.dto.AccountSubjectSummary;
import cn.com.starest.nextoa.project.web.dto.SaveAccountSubjectParameter;

import java.util.List;

/**
 * 会计科目
 *
 * @author dz
 */
public interface AccountSubjectRestSupport {

	List<Module> listSupportedModules();

	AccountSubjectSummary createAccountSubject(SaveAccountSubjectParameter request, User user);

	AccountSubjectSummary updateAccountSubject(String id, SaveAccountSubjectParameter request, User user);

	AccountSubjectSummary addAccountSubjectChild(String id, SaveAccountSubjectParameter request, User user);

	void deleteAccountSubjectById(String id, User user);

	AccountSubjectSummary findAccountSubjectById(String id);

	List<AccountSubjectSummary> listAccountSubjects();

	List<AccountSubjectSummary> listAccountSubjectChildren(String id);

	List<AccountSubjectSummary> listAccountSubjectsByModule(Module module);
}
