package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.SmsHistory;
import in.clouthink.nextoa.model.dtr.SmsHistoryQueryRequest;
import org.springframework.data.domain.Page;

public interface SmsHistoryService {
    
    Page<SmsHistory> findPage(SmsHistoryQueryRequest request);
}
