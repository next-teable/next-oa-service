package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import cn.com.starest.nextoa.project.domain.request.CompanyCapitalQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface CompanyCapitalRepositoryCustom {

	Page<CompanyCapital> queryPage(CompanyCapitalQueryRequest request);

}
