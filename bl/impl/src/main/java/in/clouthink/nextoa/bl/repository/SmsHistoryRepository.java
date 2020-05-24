package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.repository.custom.SmsHistoryRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SmsHistoryRepository extends AbstractRepository<SmsHistory>, SmsHistoryRepositoryCustom {

	Page<SmsHistory> findByCategory(SmsHistory.SmsCategory category, Pageable pageable);

}
