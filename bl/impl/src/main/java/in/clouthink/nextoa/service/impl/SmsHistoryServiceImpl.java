package in.clouthink.nextoa.service.impl;

import in.clouthink.nextoa.repository.SmsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import in.clouthink.nextoa.model.SmsHistory;
import in.clouthink.nextoa.model.dtr.SmsHistoryQueryRequest;
import in.clouthink.nextoa.service.SmsHistoryService;

@Service
public class SmsHistoryServiceImpl implements SmsHistoryService {
    
    @Autowired
    private SmsHistoryRepository repository;
    
    @Override
    public Page<SmsHistory> findPage(SmsHistoryQueryRequest request) {
        return repository.queryPage(request);
    }
}
