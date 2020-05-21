package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.dto.ReimburseSummary;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface ProjectProfitRestSupport {

	ProjectSettlementSummary findProjectSettlementById(String id, User byWho);

	Page<ProjectSettlementSummary> listProjectSettlements(ProjectSettlementQueryParameter request, User byWho);

	List<PaymentSummary> getProjectSettlementPaymentList(String id, String userId, User user);

}
