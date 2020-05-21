package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.NamedPageQueryParameter;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.BaseDataRestSupport;
import cn.com.starest.nextoa.project.web.support.BizDepartmentRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("基础数据CRUD服务")
@RestController
@RequestMapping("/api")
public class BaseDataRestController {

    @Autowired
    private BaseDataRestSupport baseDataRestSupport;

    @Autowired
    private BizDepartmentRestSupport bizDepartmentRestSupport;

    @ApiOperation(value = "创建公司")
    @RequestMapping(value = "/basement/companies", method = RequestMethod.POST)
    public CompanySummary createCompany(@Validated @RequestBody Company request) {
        User user = SecurityContexts.getContext().requireUser();
        return baseDataRestSupport.createCompany(request, user);
    }

    @ApiOperation(value = "修改公司")
    @RequestMapping(value = "/basement/companies/{id}", method = RequestMethod.POST)
    public void updateCompany(@PathVariable String id, @Validated @RequestBody Company request) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.updateCompany(id, request, user);
    }

    @ApiOperation(value = "删除公司")
    @RequestMapping(value = "/basement/companies/{id}", method = RequestMethod.DELETE)
    public void deleteCompanyById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.deleteCompanyById(id, user);
    }

    @ApiOperation(value = "查询公司详情")
    @RequestMapping(value = {"/basement/companies/{id}", "/shared/companies/{id}"}, method = RequestMethod.GET)
    public CompanySummary findCompanyById(@PathVariable String id) {
        return baseDataRestSupport.findCompanyById(id);
    }

    @ApiOperation(value = "查询公司列表")
    @RequestMapping(value = {"/basement/companies", "/shared/companies"}, method = RequestMethod.GET)
    public List<CompanySummary> listCompanies() {
        return baseDataRestSupport.listCompanies();
    }

    @ApiOperation(value = "创建招标代理单位")
    @RequestMapping(value = "/basement/biddingAgents", method = RequestMethod.POST)
    public BiddingAgentSummary createBiddingAgent(@Validated @RequestBody BiddingAgent request) {
        User user = SecurityContexts.getContext().requireUser();
        return baseDataRestSupport.createBiddingAgent(request, user);
    }

    @ApiOperation(value = "修改招标代理单位")
    @RequestMapping(value = "/basement/biddingAgents/{id}", method = RequestMethod.POST)
    public void updateBiddingAgent(@PathVariable String id, @Validated @RequestBody BiddingAgent request) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.updateBiddingAgent(id, request, user);
    }

    @ApiOperation(value = "删除招标代理单位")
    @RequestMapping(value = "/basement/biddingAgents/{id}", method = RequestMethod.DELETE)
    public void deleteBiddingAgentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.deleteBiddingAgentById(id, user);
    }

    @ApiOperation(value = "查询招标代理单位详情")
    @RequestMapping(value = {"/basement/biddingAgents/{id}", "/shared/biddingAgents/{id}"}, method = RequestMethod.GET)
    public BiddingAgentSummary findBiddingAgentById(@PathVariable String id) {
        return baseDataRestSupport.findBiddingAgentById(id);
    }

    @ApiOperation(value = "查询招标代理单位列表")
    @RequestMapping(value = {"/basement/biddingAgents", "/shared/biddingAgents"}, method = RequestMethod.GET)
    public Page<BiddingAgentSummary> listBiddingAgents(NamedPageQueryParameter parameter) {
        return baseDataRestSupport.listBiddingAgents(parameter);
    }

    @ApiOperation(value = "创建框架合同")
    @RequestMapping(value = "/basement/frameworkContracts", method = RequestMethod.POST)
    public FrameworkContractSummary createFrameworkContract(@Validated @RequestBody FrameworkContract request) {
        User user = SecurityContexts.getContext().requireUser();
        return baseDataRestSupport.createFrameworkContract(request, user);
    }

    @ApiOperation(value = "修改框架合同")
    @RequestMapping(value = "/basement/frameworkContracts/{id}", method = RequestMethod.POST)
    public void updateFrameworkContract(@PathVariable String id, @Validated @RequestBody FrameworkContract request) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.updateFrameworkContract(id, request, user);
    }

    @ApiOperation(value = "删除框架合同")
    @RequestMapping(value = "/basement/frameworkContracts/{id}", method = RequestMethod.DELETE)
    public void deleteFrameworkContractById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.deleteFrameworkContractById(id, user);
    }

    @ApiOperation(value = "查询框架合同详情")
    @RequestMapping(value = {"/basement/frameworkContracts/{id}", "/shared/frameworkContracts/{id}"}, method = RequestMethod.GET)
    public FrameworkContractSummary findFrameworkContractById(@PathVariable String id) {
        return baseDataRestSupport.findFrameworkContractById(id);
    }

    @ApiOperation(value = "查询框架合同列表")
    @RequestMapping(value = {"/basement/frameworkContracts", "/shared/frameworkContracts"}, method = RequestMethod.GET)
    public Page<FrameworkContractSummary> listFrameworkContracts(FrameworkContractQueryParameter parameter) {
        return baseDataRestSupport.listFrameworkContracts(parameter);
    }

    @ApiOperation(value = "创建甲方")
    @RequestMapping(value = "/basement/firstParties", method = RequestMethod.POST)
    public FirstPartySummary createFirstParty(@Validated @RequestBody FirstParty request) {
        User user = SecurityContexts.getContext().requireUser();
        return baseDataRestSupport.createFirstParty(request, user);
    }

    @ApiOperation(value = "修改甲方")
    @RequestMapping(value = "/basement/firstParties/{id}", method = RequestMethod.POST)
    public void updateFirstParty(@PathVariable String id, @Validated @RequestBody FirstParty request) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.updateFirstParty(id, request, user);
    }

    @ApiOperation(value = "删除甲方")
    @RequestMapping(value = "/basement/firstParties/{id}", method = RequestMethod.DELETE)
    public void deleteFirstPartyById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.deleteFirstPartyById(id, user);
    }

    @ApiOperation(value = "查询甲方详情")
    @RequestMapping(value = {"/basement/firstParties/{id}", "/shared/firstParties/{id}"}, method = RequestMethod.GET)
    public FirstPartySummary findFirstPartyById(@PathVariable String id) {
        return baseDataRestSupport.findFirstPartyById(id);
    }

    @ApiOperation(value = "查询甲方列表")
    @RequestMapping(value = {"/basement/firstParties", "/shared/firstParties"}, method = RequestMethod.GET)
    public Page<FirstPartySummary> listFirstParties(NamedPageQueryParameter parameter) {
        return baseDataRestSupport.listFirstParties(parameter);
    }

    @ApiOperation(value = "创建分包单位")
    @RequestMapping(value = "/basement/subContractors", method = RequestMethod.POST)
    public SubContractorSummary createSubContractor(@Validated @RequestBody SubContractor request) {
        User user = SecurityContexts.getContext().requireUser();
        return baseDataRestSupport.createSubContractor(request, user);
    }

    @ApiOperation(value = "修改分包单位")
    @RequestMapping(value = "/basement/subContractors/{id}", method = RequestMethod.POST)
    public void updateSubContractor(@PathVariable String id, @Validated @RequestBody SubContractor request) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.updateSubContractor(id, request, user);
    }

    @ApiOperation(value = "删除分包单位")
    @RequestMapping(value = "/basement/subContractors/{id}", method = RequestMethod.DELETE)
    public void deleteSubContractorById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        baseDataRestSupport.deleteSubContractorById(id, user);
    }

    @ApiOperation(value = "查询分包单位详情")
    @RequestMapping(value = {"/basement/subContractors/{id}",
            "/shared/subContractors/{id}"}, method = RequestMethod.GET)
    public SubContractorSummary findSubContractorById(@PathVariable String id) {
        return baseDataRestSupport.findSubContractorById(id);
    }

    @ApiOperation(value = "查询分包单位列表")
    @RequestMapping(value = {"/basement/subContractors", "/shared/subContractors"}, method = RequestMethod.GET)
    public Page<SubContractorSummary> listSubContractors(NamedPageQueryParameter parameter) {
        return baseDataRestSupport.listSubContractors(parameter);
    }

    @ApiOperation(value = "创建业务部门")
    @RequestMapping(value = "/basement/bizDepartments", method = RequestMethod.POST)
    public BizDepartmentSummary createBizDepartment(@Validated @RequestBody SaveBizDepartmentParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        return bizDepartmentRestSupport.createBizDepartment(request, user);
    }

    @ApiOperation(value = "修改业务部门")
    @RequestMapping(value = "/basement/bizDepartments/{id}", method = RequestMethod.POST)
    public void updateBizDepartment(@PathVariable String id,
                                    @Validated @RequestBody SaveBizDepartmentParameter request) {
        User user = SecurityContexts.getContext().requireUser();
        bizDepartmentRestSupport.updateBizDepartment(id, request, user);
    }

    @ApiOperation(value = "删除业务部门")
    @RequestMapping(value = "/basement/bizDepartments/{id}", method = RequestMethod.DELETE)
    public void deleteBizDepartmentById(@PathVariable String id) {
        User user = SecurityContexts.getContext().requireUser();
        bizDepartmentRestSupport.deleteBizDepartmentById(id, user);
    }

    @ApiOperation(value = "查询业务部门详情")
    @RequestMapping(value = {"/basement/bizDepartments/{id}",
            "/shared/bizDepartments/{id}"}, method = RequestMethod.GET)
    public BizDepartmentSummary findBizDepartmentById(@PathVariable String id) {
        return bizDepartmentRestSupport.findBizDepartmentById(id);
    }

    @ApiOperation(value = "查询业务部门列表")
    @RequestMapping(value = {"/basement/bizDepartments", "/shared/bizDepartments"}, method = RequestMethod.GET)
    public List<BizDepartmentSummary> listBizDepartments() {
        return bizDepartmentRestSupport.listBizDepartments();
    }

    @ApiOperation(value = "查询业务部门列表")
    @RequestMapping(value = {"/shared/bizDepartments/type/{type}"}, method = RequestMethod.GET)
    public List<BizDepartmentSummary> listBizDepartmentsByType(@PathVariable("type") String typeValue) {
        BizDepartmentType type = BizDepartmentType.valueOf(typeValue);
        if (null == type) {
            return new ArrayList<>();
        }
        return bizDepartmentRestSupport.listBizDepartmentsByType(type);
    }

}
