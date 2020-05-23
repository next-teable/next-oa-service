package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Notice;
import in.clouthink.nextoa.repository.custom.NoticeRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeRepository extends AbstractRepository<Notice>, NoticeRepositoryCustom {

	Page<Notice> findByPublished(boolean published, Pageable pageable);

}
