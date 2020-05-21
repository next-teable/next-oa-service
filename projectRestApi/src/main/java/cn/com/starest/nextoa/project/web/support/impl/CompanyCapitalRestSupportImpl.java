package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyCapitalRequest;
import cn.com.starest.nextoa.project.service.CompanyCapitalService;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.CompanyCapitalRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class CompanyCapitalRestSupportImpl implements CompanyCapitalRestSupport {

	@Autowired
	private CompanyCapitalService companyCapitalService;

	@Override
	public CompanyCapitalSummary createCompanyCapital(SaveCompanyCapitalRequest request, User byWho) {
		CompanyCapital companyCapital = companyCapitalService.createCompanyCapital(request, byWho);
		CompanyCapitalSummary summary = CompanyCapitalSummary.from(companyCapital);
		summary.setGrantedActions(companyCapitalService.resolveGrantedActions(companyCapital, byWho));
		return summary;
	}

	@Override
	public CompanyCapitalSummary updateCompanyCapital(String id, SaveCompanyCapitalRequest request, User byWho) {
		CompanyCapital companyCapital = companyCapitalService.updateCompanyCapital(id, request, byWho);
		CompanyCapitalSummary summary = CompanyCapitalSummary.from(companyCapital);
		summary.setGrantedActions(companyCapitalService.resolveGrantedActions(companyCapital, byWho));
		return summary;
	}

	@Override
	public CompanyCapitalDetail findCompanyCapitalById(String id, User byWho) {
		CompanyCapital companyCapital = companyCapitalService.findCompanyCapitalById(id, byWho);
		CompanyCapitalDetail summary = CompanyCapitalDetail.from(companyCapital);
		summary.setGrantedActions(companyCapitalService.resolveGrantedActions(companyCapital, byWho));
		return summary;
	}

	@Override
	public Page<CompanyCapitalSummary> listCompanyCapitals(CompanyCapitalQueryParameter request, User byWho) {
		Page<CompanyCapital> companyCapitalPage = companyCapitalService.listCompanyCapitals(request, byWho);
		return new PermissionAwarePageImpl<>(companyCapitalPage.getContent().stream().map(item -> {
			CompanyCapitalSummary summary = CompanyCapitalSummary.from(item);
			summary.setGrantedActions(companyCapitalService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 companyCapitalPage.getTotalElements(),
											 companyCapitalService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteCompanyCapitalById(String id, User user) {
		companyCapitalService.deleteCompanyCapitalById(id, user);
	}

}
