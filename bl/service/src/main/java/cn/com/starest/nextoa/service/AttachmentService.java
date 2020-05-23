package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.AttachmentDownloadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.AttachmentQueryRequest;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.model.dtr.SaveAttachmentRequest;
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
