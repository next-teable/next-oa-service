package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.shared.repository.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;

public interface SmsHistoryRepositoryCustom extends AbstractCustomRepository {

	Page<SmsHistory> queryPage(SmsHistoryQueryRequest parameter);

}
