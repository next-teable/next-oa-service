package in.clouthink.nextoa.bl.service.impl;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.repository.SmsHistoryRepository;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.bl.service.SmsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SmsHistoryServiceImpl implements SmsHistoryService {
    
    @Autowired
    private SmsHistoryRepository repository;
    
    @Override
    public Page<SmsHistory> findPage(SmsHistoryQueryRequest request) {
        return repository.queryPage(request);
    }
}
