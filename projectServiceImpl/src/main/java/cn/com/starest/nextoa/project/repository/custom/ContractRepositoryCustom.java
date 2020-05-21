package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface ContractRepositoryCustom {

	/**
	 * 计算某项目的主合同总金额（已生效的主合同）
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project);

	/**
	 * 动态查询合同列表,支持分页
	 *
	 * @param request
	 * @return
	 */
	Page<Contract> queryPage(ContractQueryRequest request);

}
