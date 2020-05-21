package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectSettlementRestSupport {

	Page<ProjectCompletionSummary> listProjectCompletions(ProjectCompletionQueryParameter request, User byWho);

	ProjectCompletionDetail findProjectCompletionById(String id, User user);

	ProjectSettlementSummary prepareProjectSettlement(String projectCompletionId, User user);

	ProjectSettlementSummary createProjectSettlement(String id, SaveProjectSettlementParameter request, User user);

	ProjectSettlementSummary updateProjectSettlement(String id, SaveProjectSettlementParameter request, User byWho);

	void doProjectSettlement(String id, User user);

	ProjectSettlementSummary findProjectSettlementById(String id, User byWho);

	Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request, User byWho);

	void deleteProjectSettlementById(String id, User user);

	List<PaymentSummary> getProjectSettlementPaymentList(String id, String userId, User user);

}
