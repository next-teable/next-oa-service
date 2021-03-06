package in.clouthink.nextoa.bl.model;

import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 */
@Document(collection = "AttachmentDownloadHistories")
public class AttachmentDownloadHistory extends StringIdentifier {

	@Indexed
	@DBRef(lazy = true)
	private Attachment attachment;

	@Indexed
	@DBRef(lazy = true)
	private User downloadedBy;

	private Date downloadedAt;

	private Date latestDownloadedAt;

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public User getDownloadedBy() {
		return downloadedBy;
	}

	public void setDownloadedBy(User downloadedBy) {
		this.downloadedBy = downloadedBy;
	}

	public Date getDownloadedAt() {
		return downloadedAt;
	}

	public void setDownloadedAt(Date downloadedAt) {
		this.downloadedAt = downloadedAt;
	}

	public Date getLatestDownloadedAt() {
		return latestDownloadedAt;
	}

	public void setLatestDownloadedAt(Date latestDownloadedAt) {
		this.latestDownloadedAt = latestDownloadedAt;
	}
}
