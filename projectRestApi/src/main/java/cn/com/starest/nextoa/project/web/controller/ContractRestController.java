package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ContractRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("合同管理")
@RestController
@RequestMapping("/api")
public class ContractRestController {

	@Autowired
	private ContractRestSupport contractRestSupport;

	@ApiOperation(value = "创建合同")
	@RequestMapping(value = "/contracts", method = RequestMethod.POST)
	public ContractSummary createContract(@Validated @RequestBody SaveContractParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.createContract(request, user);
	}

	@ApiOperation(value = "修改合同-只能修改未发布的合同")
	@RequestMapping(value = "/contracts/{id}", method = RequestMethod.POST)
	public ContractSummary updateContract(@PathVariable String id,
										  @Validated @RequestBody SaveContractParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.updateContract(id, request, user);
	}

	@ApiOperation(value = "发布合同-发布后的合同才生效")
	@RequestMapping(value = "/contracts/{id}/publish", method = RequestMethod.POST)
	public void publishContract(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		contractRestSupport.publishContract(id, user);
	}

	@ApiOperation(value = "发布合同-发布后的合同才生效")
	@RequestMapping(value = "/contracts/{id}/unpublish", method = RequestMethod.POST)
	public void unpublishContract(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		contractRestSupport.unpublishContract(id, user);
	}

	@ApiOperation(value = "查看合同")
	@RequestMapping(value = "/contracts/{id}", method = RequestMethod.GET)
	public ContractDetail findContractById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.findContractById(id, user);
	}

	@ApiOperation(value = "删除合同-只能删除未发布的合同")
	@RequestMapping(value = "/contracts/{id}", method = RequestMethod.DELETE)
	public void deleteContractById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		contractRestSupport.deleteContractById(id, user);
	}

	@ApiOperation(value = "查看合同列表")
	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public Page<ContractSummary> listContracts(ContractQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.listContracts(request, user);
	}

	@ApiOperation(value = "查看合同修改历史详情")
	@RequestMapping(value = "/contracts/histories/{id}", method = RequestMethod.GET)
	public ContractHistorySummary findContractHistoryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.findContractHistoryById(id, user);
	}

	@ApiOperation(value = "查看合同修改历史列表")
	@RequestMapping(value = "/contracts/{id}/history", method = RequestMethod.GET)
	public Page<ContractHistorySummary> listContractHistories(@PathVariable String id, PageQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return contractRestSupport.listContractHistories(id, request, user);
	}

}
