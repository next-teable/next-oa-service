package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.SmsHistory;
import in.clouthink.nextoa.repository.custom.SmsHistoryRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SmsHistoryRepository extends AbstractRepository<SmsHistory>, SmsHistoryRepositoryCustom {

	Page<SmsHistory> findByCategory(SmsHistory.SmsCategory category, Pageable pageable);

}
