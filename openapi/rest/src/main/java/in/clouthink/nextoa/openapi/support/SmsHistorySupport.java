package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.model.dtr.SmsHistoryQueryRequest;
import org.springframework.data.domain.Page;

import in.clouthink.nextoa.openapi.dto.SmsHistorySummary;

public interface SmsHistorySupport {
    
    Page<SmsHistorySummary> findPage(SmsHistoryQueryRequest request);
}
