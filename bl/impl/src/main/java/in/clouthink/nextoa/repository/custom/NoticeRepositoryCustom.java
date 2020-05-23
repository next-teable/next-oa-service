package in.clouthink.nextoa.repository.custom;

import org.springframework.data.domain.Page;

import in.clouthink.nextoa.model.Notice;
import in.clouthink.nextoa.model.dtr.NoticeQueryRequest;

public interface NoticeRepositoryCustom {
    
    Page<Notice> queryPage(NoticeQueryRequest queryRequest);
    
    void updateReadCounter(String id, int readCounter);
}
