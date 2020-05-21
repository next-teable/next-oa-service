package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveDepositRequest;
import cn.com.starest.nextoa.project.web.dto.DepositDetail;
import cn.com.starest.nextoa.project.web.dto.DepositQueryParameter;
import cn.com.starest.nextoa.project.web.dto.DepositSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface DepositRestSupport {

	DepositSummary createDeposit(SaveDepositRequest request, User byWho);

	DepositSummary updateDeposit(String id, SaveDepositRequest request, User byWho);

	DepositDetail findDepositById(String id, User byWho);

	Page<DepositSummary> listDeposits(DepositQueryParameter request, User byWho);

	void deleteDepositById(String id, User user);

}
