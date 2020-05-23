package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.NoticeReadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NoticeReadHistoryRepository extends AbstractRepository<NoticeReadHistory> {

	Page<NoticeReadHistory> findByNotice(Notice notice, Pageable pageable);

	NoticeReadHistory findByNoticeAndReadBy(Notice notice, User user);

	int countByNotice(Notice notice);

}