package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Notice;
import in.clouthink.nextoa.bl.model.NoticeReadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface NoticeReadHistoryRepository extends AbstractRepository<NoticeReadHistory> {

	Page<NoticeReadHistory> findByNotice(Notice notice, Pageable pageable);

	NoticeReadHistory findByNoticeAndReadBy(Notice notice, User user);

	int countByNotice(Notice notice);

}
