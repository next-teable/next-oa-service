package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyCapitalRequest;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyCapitalSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface CompanyCapitalRestSupport {

	CompanyCapitalSummary createCompanyCapital(SaveCompanyCapitalRequest request, User byWho);

	CompanyCapitalSummary updateCompanyCapital(String id, SaveCompanyCapitalRequest request, User byWho);

	CompanyCapitalDetail findCompanyCapitalById(String id, User byWho);

	Page<CompanyCapitalSummary> listCompanyCapitals(CompanyCapitalQueryParameter request, User byWho);

	void deleteCompanyCapitalById(String id, User user);

}
