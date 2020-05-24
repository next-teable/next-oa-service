package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.Notice;
import in.clouthink.nextoa.bl.request.NoticeQueryRequest;
import org.springframework.data.domain.Page;

public interface NoticeRepositoryCustom {
    
    Page<Notice> queryPage(NoticeQueryRequest queryRequest);
    
    void updateReadCounter(String id, int readCounter);
}
