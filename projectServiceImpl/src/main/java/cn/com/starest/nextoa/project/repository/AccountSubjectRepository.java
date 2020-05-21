package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 */
public interface AccountSubjectRepository extends AbstractRepository<AccountSubject> {

	AccountSubject findFirstByName(String name);

	List<AccountSubject> findListByParentOrderBySortAsc(AccountSubject parent);

	List<AccountSubject> findListByModulesInOrderBySortAsc(Module module);

	Page<AccountSubject> findPageByParent(AccountSubject accountSubject, Pageable pageable);

	Page<AccountSubject> findPageByParentIsNullAndModulesIn(Module module, Pageable pageable);

	AccountSubject findFirstByParent(AccountSubject target);
}