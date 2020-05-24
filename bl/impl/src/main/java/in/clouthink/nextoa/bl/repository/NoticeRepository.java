package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Notice;
import in.clouthink.nextoa.bl.repository.custom.NoticeRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeRepository extends AbstractRepository<Notice>, NoticeRepositoryCustom {

	Page<Notice> findByPublished(boolean published, Pageable pageable);

}
