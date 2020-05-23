package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.model.AttachmentDownloadHistory;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface AttachmentDownloadHistoryRepository extends AbstractRepository<AttachmentDownloadHistory> {

	Page<AttachmentDownloadHistory> findByAttachment(Attachment attachment, Pageable pageable);

	AttachmentDownloadHistory findByAttachmentAndDownloadedBy(Attachment attachment, User user);

	int countByAttachment(Attachment attachment);

}
