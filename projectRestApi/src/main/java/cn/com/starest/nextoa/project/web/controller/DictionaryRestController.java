package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.DictionarySummary;
import cn.com.starest.nextoa.project.web.dto.SaveDictionaryParameter;
import cn.com.starest.nextoa.project.web.support.DictionaryRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("数据字典CRUD服务")
@RestController
@RequestMapping("/api")
public class DictionaryRestController {

	@Autowired
	private DictionaryRestSupport dictionaryRestSupport;

	@ApiOperation(value = "创建项目类型")
	@RequestMapping(value = {"/dictionary/projectTypes"}, method = RequestMethod.POST)
	public DictionarySummary createProjectType(@Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.createProjectType(request, user);
	}

	@ApiOperation(value = "修改项目类型")
	@RequestMapping(value = "/dictionary/projectTypes/{id}", method = RequestMethod.POST)
	public DictionarySummary updateProjectType(@PathVariable String id,
											   @Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.updateProjectType(id, request, user);
	}

	@ApiOperation(value = "查询项目类型详情")
	@RequestMapping(value = {"/dictionary/projectTypes/{id}", "/shared/projectTypes/{id}"}, method = RequestMethod.GET)
	public DictionarySummary findProjectTypeById(@PathVariable String id) {
		return dictionaryRestSupport.findProjectTypeById(id);
	}

	@ApiOperation(value = "查询项目类型列表")
	@RequestMapping(value = {"/dictionary/projectTypes", "/shared/projectTypes"}, method = RequestMethod.GET)
	public List<DictionarySummary> listProjectTypes() {
		return dictionaryRestSupport.listProjectTypes();
	}

	@ApiOperation(value = "创建项目交付方式")
	@RequestMapping(value = "/dictionary/projectDeliveryTypes", method = RequestMethod.POST)
	public DictionarySummary createProjectDeliveryType(@Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.createProjectDeliveryType(request, user);
	}

	@ApiOperation(value = "修改项目交付方式")
	@RequestMapping(value = "/dictionary/projectDeliveryTypes/{id}", method = RequestMethod.POST)
	public DictionarySummary updateProjectDeliveryType(@PathVariable String id,
													   @Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.updateProjectDeliveryType(id, request, user);
	}

	@ApiOperation(value = "查询项目交付方式详情")
	@RequestMapping(value = {"/dictionary/projectDeliveryTypes/{id}",
							 "/shared/projectDeliveryTypes/{id}"}, method = RequestMethod.GET)
	public DictionarySummary findProjectDeliveryTypeById(@PathVariable String id) {
		return dictionaryRestSupport.findProjectDeliveryTypeById(id);
	}

	@ApiOperation(value = "查询项目交付方式列表")
	@RequestMapping(value = {"/dictionary/projectDeliveryTypes",
							 "/shared/projectDeliveryTypes"}, method = RequestMethod.GET)
	public List<DictionarySummary> listProjectDeliveryTypes() {
		return dictionaryRestSupport.listProjectDeliveryTypes();
	}

	@ApiOperation(value = "创建合同类型")
	@RequestMapping(value = "/dictionary/contractTypes", method = RequestMethod.POST)
	public DictionarySummary createContractType(@Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.createContractType(request, user);
	}

	@ApiOperation(value = "修改合同类型")
	@RequestMapping(value = "/dictionary/contractTypes/{id}", method = RequestMethod.POST)
	public DictionarySummary updateContractType(@PathVariable String id,
												@Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.updateContractType(id, request, user);
	}

	@ApiOperation(value = "查询合同类型详情")
	@RequestMapping(value = {"/dictionary/contractTypes/{id}",
							 "/shared/contractTypes/{id}"}, method = RequestMethod.GET)
	public DictionarySummary findContractTypeById(@PathVariable String id) {
		return dictionaryRestSupport.findContractTypeById(id);
	}

	@ApiOperation(value = "查询合同类型列表")
	@RequestMapping(value = {"/dictionary/contractTypes", "/shared/contractTypes"}, method = RequestMethod.GET)
	public List<DictionarySummary> listContractTypes() {
		return dictionaryRestSupport.listContractTypes();
	}

	@ApiOperation(value = "创建保证金类型")
	@RequestMapping(value = "/dictionary/depositTypes", method = RequestMethod.POST)
	public DictionarySummary createDepositType(@Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.createDepositType(request, user);
	}

	@ApiOperation(value = "修改保证金类型")
	@RequestMapping(value = "/dictionary/depositTypes/{id}", method = RequestMethod.POST)
	public DictionarySummary updateDepositType(@PathVariable String id,
											   @Validated @RequestBody SaveDictionaryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return dictionaryRestSupport.updateDepositType(id, request, user);
	}

	@ApiOperation(value = "查询保证金类型详情")
	@RequestMapping(value = {"/dictionary/depositTypes/{id}", "/shared/depositTypes/{id}"}, method = RequestMethod.GET)
	public DictionarySummary findDepositTypeById(@PathVariable String id) {
		return dictionaryRestSupport.findDepositTypeById(id);
	}

	@ApiOperation(value = "查询保证金类型列表")
	@RequestMapping(value = {"/dictionary/depositTypes", "/shared/depositTypes"}, method = RequestMethod.GET)
	public List<DictionarySummary> listDepositTypes() {
		return dictionaryRestSupport.listDepositTypes();
	}

}
