package in.clouthink.nextoa.bl.model;

import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 */
@Document(collection = "NoticeReadHistories")
public class NoticeReadHistory extends StringIdentifier {

	@Indexed
	@DBRef(lazy = true)
	private Notice notice;

	@Indexed
	@DBRef(lazy = true)
	private User readBy;

	private Date readAt;

	private Date latestReadAt;

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public User getReadBy() {
		return readBy;
	}

	public void setReadBy(User readBy) {
		this.readBy = readBy;
	}

	public Date getReadAt() {
		return readAt;
	}

	public void setReadAt(Date readAt) {
		this.readAt = readAt;
	}

	public Date getLatestReadAt() {
		return latestReadAt;
	}

	public void setLatestReadAt(Date latestReadAt) {
		this.latestReadAt = latestReadAt;
	}
}
