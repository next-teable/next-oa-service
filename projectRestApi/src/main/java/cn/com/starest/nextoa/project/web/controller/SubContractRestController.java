package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.SubContractRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("分包合同管理")
@RestController
@RequestMapping("/api")
public class SubContractRestController {

	@Autowired
	private SubContractRestSupport subContractRestSupport;

	@ApiOperation(value = "创建分包合同")
	@RequestMapping(value = "/subContracts", method = RequestMethod.POST)
	public SubContractSummary createSubContract(@Validated @RequestBody SaveSubContractParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.createSubContract(request, user);
	}

	@ApiOperation(value = "修改分包合同-只能修改未发布的分包合同")
	@RequestMapping(value = "/subContracts/{id}", method = RequestMethod.POST)
	public SubContractSummary updateSubContract(@PathVariable String id,
												@Validated @RequestBody SaveSubContractParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.updateSubContract(id, request, user);
	}

	@ApiOperation(value = "发布分包合同-发布后的分包合同才生效")
	@RequestMapping(value = "/subContracts/{id}/publish", method = RequestMethod.POST)
	public void publishSubContract(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subContractRestSupport.publishSubContract(id, user);
	}

	@ApiOperation(value = "发布分包合同-发布后的分包合同才生效")
	@RequestMapping(value = "/subContracts/{id}/unpublish", method = RequestMethod.POST)
	public void unpublishSubContract(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subContractRestSupport.unpublishSubContract(id, user);
	}

	@ApiOperation(value = "查看分包合同")
	@RequestMapping(value = "/subContracts/{id}", method = RequestMethod.GET)
	public SubContractDetail findSubContractById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.findSubContractById(id, user);
	}

	@ApiOperation(value = "删除分包合同-只能删除未发布的分包合同")
	@RequestMapping(value = "/subContracts/{id}", method = RequestMethod.DELETE)
	public void deleteSubContractById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		subContractRestSupport.deleteSubContractById(id, user);
	}

	@ApiOperation(value = "查看分包合同列表")
	@RequestMapping(value = "/subContracts", method = RequestMethod.GET)
	public Page<SubContractSummary> listSubContracts(SubContractQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.listSubContracts(request, user);
	}

	@ApiOperation(value = "查看分包合同修改历史详情")
	@RequestMapping(value = "/subContracts/histories/{id}", method = RequestMethod.GET)
	public SubContractHistorySummary findSubContractHistoryById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.findSubContractHistoryById(id, user);
	}

	@ApiOperation(value = "查看分包合同修改历史列表")
	@RequestMapping(value = "/subContracts/{id}/history", method = RequestMethod.GET)
	public Page<SubContractHistorySummary> listSubContractHistories(@PathVariable String id,
																	PageQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return subContractRestSupport.listSubContractHistories(id, request, user);
	}

}
