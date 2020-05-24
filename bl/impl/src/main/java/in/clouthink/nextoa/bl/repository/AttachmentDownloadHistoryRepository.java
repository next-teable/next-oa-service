package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Attachment;
import in.clouthink.nextoa.bl.model.AttachmentDownloadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 */
public interface AttachmentDownloadHistoryRepository extends AbstractRepository<AttachmentDownloadHistory> {

	Page<AttachmentDownloadHistory> findByAttachment(Attachment attachment, Pageable pageable);

	AttachmentDownloadHistory findByAttachmentAndDownloadedBy(Attachment attachment, User user);

	int countByAttachment(Attachment attachment);

}
