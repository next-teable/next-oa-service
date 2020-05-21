package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.web.dto.AccountSubjectSummary;
import cn.com.starest.nextoa.project.web.dto.SaveAccountSubjectParameter;
import cn.com.starest.nextoa.project.web.support.AccountSubjectRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("会计科目CRUD服务")
@RestController
@RequestMapping("/api")
public class AccountSubjectRestController {

	@Autowired
	private AccountSubjectRestSupport accountSubjectRestSupport;

	@ApiOperation(value = "会计科目分类标签")
	@RequestMapping(value = "/accountSubjects/supportedModules", method = RequestMethod.GET)
	public List<Module> listSupportedModules() {
		return accountSubjectRestSupport.listSupportedModules();
	}

	@ApiOperation(value = "创建费用类型（会计科目）")
	@RequestMapping(value = "/accountSubjects", method = RequestMethod.POST)
	public AccountSubjectSummary createAccountSubject(@Validated @RequestBody SaveAccountSubjectParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return accountSubjectRestSupport.createAccountSubject(request, user);
	}

	@ApiOperation(value = "修改费用类型（会计科目）")
	@RequestMapping(value = "/accountSubjects/{id}", method = RequestMethod.POST)
	public void updateAccountSubject(@PathVariable String id,
									 @Validated @RequestBody SaveAccountSubjectParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		accountSubjectRestSupport.updateAccountSubject(id, request, user);
	}

	@ApiOperation(value = "增加二级费用类型（二级会计科目）")
	@RequestMapping(value = "/accountSubjects/{id}/children", method = RequestMethod.POST)
	public AccountSubjectSummary addAccountSubjectChild(@PathVariable String id,
														@Validated @RequestBody SaveAccountSubjectParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return accountSubjectRestSupport.addAccountSubjectChild(id, request, user);
	}

	@ApiOperation(value = "删除费用类型（会计科目）")
	@RequestMapping(value = "/accountSubjects/{id}", method = RequestMethod.DELETE)
	public void deleteAccountSubjectById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		accountSubjectRestSupport.deleteAccountSubjectById(id, user);
	}

	@ApiOperation(value = "查询费用类型（会计科目）详情")
	@RequestMapping(value = {"/accountSubjects/{id}", "/shared/accountSubjects/{id}"}, method = RequestMethod.GET)
	public AccountSubjectSummary findAccountSubjectById(@PathVariable String id) {
		return accountSubjectRestSupport.findAccountSubjectById(id);
	}

	@ApiOperation(value = "查询费用类型（会计科目）列表")
	@RequestMapping(value = {"/accountSubjects", "/shared/accountSubjects"}, method = RequestMethod.GET)
	public List<AccountSubjectSummary> listAccountSubjects() {
		return accountSubjectRestSupport.listAccountSubjects();
	}


	@ApiOperation(value = "根据模块过滤查询费用类型（会计科目）列表")
	@RequestMapping(value = {"/accountSubjects/byModule/{module}",
							 "/shared/accountSubjects/byModule/{module}"}, method = RequestMethod.GET)
	public List<AccountSubjectSummary> listAccountSubjectsByModule(@PathVariable Module module) {
		return accountSubjectRestSupport.listAccountSubjectsByModule(module);
	}

	@ApiOperation(value = "查询二级费用类型（二级会计科目）")
	@RequestMapping(value = {"/accountSubjects/{id}/children",
							 "/shared/accountSubjects/{id}/children"}, method = RequestMethod.GET)
	public List<AccountSubjectSummary> listAccountSubjectChildren(@PathVariable String id) {
		return accountSubjectRestSupport.listAccountSubjectChildren(id);
	}

}
