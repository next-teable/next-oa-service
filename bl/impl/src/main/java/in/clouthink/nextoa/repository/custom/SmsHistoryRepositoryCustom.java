package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.SmsHistory;
import in.clouthink.nextoa.model.dtr.SmsHistoryQueryRequest;
import in.clouthink.nextoa.repository.shared.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;

public interface SmsHistoryRepositoryCustom extends AbstractCustomRepository {

	Page<SmsHistory> queryPage(SmsHistoryQueryRequest parameter);

}
