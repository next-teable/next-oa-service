package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SubOrderRepositoryCustom {

	BigDecimal calculateTotalAmount(Project project);

	Page<SubOrder> queryPage(SubOrderQueryRequest request);

}
