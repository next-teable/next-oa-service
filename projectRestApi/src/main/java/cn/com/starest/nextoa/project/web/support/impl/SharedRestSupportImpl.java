package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.service.ProjectService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.ContractRestSupport;
import cn.com.starest.nextoa.project.web.support.OrderRestSupport;
import cn.com.starest.nextoa.project.web.support.SharedRestSupport;
import cn.com.starest.nextoa.project.web.support.SubContractRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class SharedRestSupportImpl implements SharedRestSupport {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private OrderRestSupport orderRestSupport;

	@Autowired
	private ContractRestSupport contractRestSupport;

	@Autowired
	private SubContractRestSupport subContractRestSupport;

	@Override
	public ProjectDetail findProjectById(String id, User byWho) {
		Project project = projectService.findProjectById(id, byWho);
		ProjectDetail result = ProjectDetail.from(project);
		return result;
	}

	@Override
	public Page<ProjectSummary> listProjects(ProjectQueryParameter request, User byWho) {
		request.setStatus(ProjectStatus.RUNNING);
		Page<Project> projectPage = projectService.listProjects(request, byWho);
		return new PageImpl<>(projectPage.getContent().stream().map(project -> {
			ProjectSummary projectSummary = ProjectSummary.from(project);
			return projectSummary;
		}).collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  projectPage.getTotalElements());
	}

	@Override
	public List<OrderSummary> listProjectOrders(String id, User user) {
		OrderQueryParameter queryParameter = new OrderQueryParameter();
		queryParameter.setProjectId(id);
		queryParameter.setStart(0);
		queryParameter.setLimit(100);
		return orderRestSupport.listOrders(queryParameter, user).getContent();
	}

	@Override
	public List<ContractSummary> listProjectContracts(String id, User user) {
		ContractQueryParameter queryParameter = new ContractQueryParameter();
		queryParameter.setProjectId(id);
		queryParameter.setStart(0);
		queryParameter.setLimit(100);
		return contractRestSupport.listContracts(queryParameter, user).getContent();
	}

	@Override
	public List<SubContractSummary> listProjectSubContracts(String id, User user) {
		SubContractQueryParameter queryParameter = new SubContractQueryParameter();
		queryParameter.setProjectId(id);
		queryParameter.setStart(0);
		queryParameter.setLimit(100);
		return subContractRestSupport.listSubContracts(queryParameter, user).getContent();
	}
}
