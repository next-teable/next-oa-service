package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.repository.custom.AttachmentRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface AttachmentRepository extends AbstractRepository<Attachment>, AttachmentRepositoryCustom {

	Page<Attachment> findByPublished(boolean published, Pageable pageable);

}