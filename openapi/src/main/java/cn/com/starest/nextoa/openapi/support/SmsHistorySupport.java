package cn.com.starest.nextoa.openapi.support;

import org.springframework.data.domain.Page;

import cn.com.starest.nextoa.model.dtr.SmsHistoryQueryRequest;
import cn.com.starest.nextoa.openapi.dto.SmsHistorySummary;

public interface SmsHistorySupport {
    
    Page<SmsHistorySummary> findPage(SmsHistoryQueryRequest request);
}
