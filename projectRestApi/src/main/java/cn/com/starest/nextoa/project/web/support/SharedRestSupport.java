package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface SharedRestSupport {

	ProjectDetail findProjectById(String id, User user);

	Page<ProjectSummary> listProjects(ProjectQueryParameter request, User user);

	List<OrderSummary> listProjectOrders(String id, User user);

	List<ContractSummary> listProjectContracts(String id, User user);

	List<SubContractSummary> listProjectSubContracts(String id, User user);

}
