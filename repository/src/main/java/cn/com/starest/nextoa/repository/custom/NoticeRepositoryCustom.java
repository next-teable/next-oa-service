package cn.com.starest.nextoa.repository.custom;

import org.springframework.data.domain.Page;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.dtr.NoticeQueryRequest;

public interface NoticeRepositoryCustom {
    
    Page<Notice> queryPage(NoticeQueryRequest queryRequest);
    
    void updateReadCounter(String id, int readCounter);
}
