package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.NoticeCategory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface NoticeCategoryRepository extends AbstractRepository<NoticeCategory> {

	Page<NoticeCategory> findByCreatedBy(User createdBy, Pageable pageable);

}