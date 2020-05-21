package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.SmsHistory;
import cn.com.starest.nextoa.repository.custom.SmsHistoryRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SmsHistoryRepository extends AbstractRepository<SmsHistory>, SmsHistoryRepositoryCustom {

	Page<SmsHistory> findByCategory(SmsHistory.SmsCategory category, Pageable pageable);

}
