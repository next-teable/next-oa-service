package cn.com.starest.nextoa.service;

import org.springframework.data.domain.Page;

import cn.com.starest.nextoa.model.SmsHistory;
import cn.com.starest.nextoa.model.dtr.SmsHistoryQueryRequest;

public interface SmsHistoryService {
    
    Page<SmsHistory> findPage(SmsHistoryQueryRequest request);
}
