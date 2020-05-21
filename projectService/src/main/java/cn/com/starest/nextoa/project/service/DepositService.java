package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.DepositQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveDepositRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface DepositService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(Deposit deposit, User byWho);

	Deposit createDeposit(SaveDepositRequest request, User byWho);

	Deposit updateDeposit(String id, SaveDepositRequest request, User byWho);

	void deleteDepositById(String id, User byWho);

	Deposit findDepositById(String id, User byWho);

	Page<Deposit> listDeposits(DepositQueryRequest request, User byWho);

	List<Deposit> listDepositsByProject(String projectId, User byWho);

	List<Deposit> listDepositsByProject(Project project, User byWho);

}
