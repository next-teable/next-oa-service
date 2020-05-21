package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.repository.custom.NoticeRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeRepository extends AbstractRepository<Notice>, NoticeRepositoryCustom {

	Page<Notice> findByPublished(boolean published, Pageable pageable);

}