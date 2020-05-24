package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.Attachment;
import in.clouthink.nextoa.bl.model.AttachmentDownloadHistory;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.AttachmentQueryRequest;
import in.clouthink.nextoa.bl.request.SaveAttachmentRequest;
import in.clouthink.nextoa.shared.domain.request.PageQueryRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface AttachmentService {

	Page<Attachment> listAttachments(AttachmentQueryRequest queryParameter);

	Attachment findAttachmentById(String id);

	void markAttachmentAsDownloaded(Attachment attachment, User user);

	boolean isAttachmentDownloadedByUser(Attachment attachment, User user);

	int countAttachmentDownloadHistory(Attachment attachment);

	Attachment createAttachment(SaveAttachmentRequest parameter, User user);

	void updateAttachment(String id, SaveAttachmentRequest parameter, User user);

	void deleteAttachment(String id, User user);

	void publishAttachment(String id, User user);

	void unpublishAttachment(String id, User user);

	Page<AttachmentDownloadHistory> listDownloadHistory(String id, PageQueryRequest queryRequest);
}
