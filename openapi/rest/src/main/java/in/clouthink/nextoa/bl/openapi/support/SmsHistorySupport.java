package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.openapi.dto.SmsHistorySummary;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import org.springframework.data.domain.Page;

public interface SmsHistorySupport {
    
    Page<SmsHistorySummary> findPage(SmsHistoryQueryRequest request);
}
