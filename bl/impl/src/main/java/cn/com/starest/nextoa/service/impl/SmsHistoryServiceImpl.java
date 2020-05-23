package cn.com.starest.nextoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import cn.com.starest.nextoa.model.SmsHistory;
import cn.com.starest.nextoa.model.dtr.SmsHistoryQueryRequest;
import cn.com.starest.nextoa.repository.SmsHistoryRepository;
import cn.com.starest.nextoa.service.SmsHistoryService;

@Service
public class SmsHistoryServiceImpl implements SmsHistoryService {
    
    @Autowired
    private SmsHistoryRepository repository;
    
    @Override
    public Page<SmsHistory> findPage(SmsHistoryQueryRequest request) {
        return repository.queryPage(request);
    }
}
