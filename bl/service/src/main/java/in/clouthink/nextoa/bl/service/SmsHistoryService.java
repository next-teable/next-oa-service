package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import org.springframework.data.domain.Page;

public interface SmsHistoryService {
    
    Page<SmsHistory> findPage(SmsHistoryQueryRequest request);
}
