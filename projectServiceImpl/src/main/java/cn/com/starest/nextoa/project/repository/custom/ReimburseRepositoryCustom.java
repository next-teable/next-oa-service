package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.request.ReimburseQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ReimburseRepositoryCustom {

	Page<Reimburse> queryPage(ReimburseQueryRequest request);

}
