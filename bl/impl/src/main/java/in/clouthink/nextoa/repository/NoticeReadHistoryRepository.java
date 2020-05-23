package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Notice;
import in.clouthink.nextoa.model.NoticeReadHistory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NoticeReadHistoryRepository extends AbstractRepository<NoticeReadHistory> {

	Page<NoticeReadHistory> findByNotice(Notice notice, Pageable pageable);

	NoticeReadHistory findByNoticeAndReadBy(Notice notice, User user);

	int countByNotice(Notice notice);

}
