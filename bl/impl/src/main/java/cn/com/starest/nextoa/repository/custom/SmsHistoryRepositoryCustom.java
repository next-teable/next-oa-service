package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.SmsHistory;
import cn.com.starest.nextoa.model.dtr.SmsHistoryQueryRequest;
import cn.com.starest.nextoa.repository.shared.custom.AbstractCustomRepository;
import org.springframework.data.domain.Page;

public interface SmsHistoryRepositoryCustom extends AbstractCustomRepository {

	Page<SmsHistory> queryPage(SmsHistoryQueryRequest parameter);

}
