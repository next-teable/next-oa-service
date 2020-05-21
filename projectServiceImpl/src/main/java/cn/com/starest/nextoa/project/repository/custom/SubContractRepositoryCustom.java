package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface SubContractRepositoryCustom {

	Page<SubContract> queryPage(SubContractQueryRequest request);

}
