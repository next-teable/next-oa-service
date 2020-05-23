package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.AttachmentDownloadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface AttachmentDownloadHistoryRepository extends AbstractRepository<AttachmentDownloadHistory> {

	Page<AttachmentDownloadHistory> findByAttachment(Attachment attachment, Pageable pageable);

	AttachmentDownloadHistory findByAttachmentAndDownloadedBy(Attachment attachment, User user);

	int countByAttachment(Attachment attachment);

}