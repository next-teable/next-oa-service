package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.NewsReadHistory;
import in.clouthink.nextoa.model.NoticeReadHistory;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 */
@ApiModel
public class ReadSummary {

	public static ReadSummary from(NewsReadHistory newsReadHistory) {
		ReadSummary result = new ReadSummary();
		result.setId(newsReadHistory.getId());
		if (newsReadHistory.getReadBy() != null) {
			result.setReadById(newsReadHistory.getReadBy().getId());
			result.setReadByName(newsReadHistory.getReadBy().getUsername());
		}
		result.setReadAt(newsReadHistory.getReadAt());
		result.setLatestReadAt(newsReadHistory.getLatestReadAt());
		return result;
	}

	public static ReadSummary from(NoticeReadHistory noticeReadHistory) {
		ReadSummary result = new ReadSummary();
		result.setId(noticeReadHistory.getId());
		if (noticeReadHistory.getReadBy() != null) {
			result.setReadById(noticeReadHistory.getReadBy().getId());
			result.setReadByName(noticeReadHistory.getReadBy().getUsername());
		}
		result.setReadAt(noticeReadHistory.getReadAt());
		result.setLatestReadAt(noticeReadHistory.getLatestReadAt());
		return result;
	}

	private String id;

	private String readById;

	private String readByName;

	private Date readAt;

	private Date latestReadAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReadById() {
		return readById;
	}

	public void setReadById(String readById) {
		this.readById = readById;
	}

	public String getReadByName() {
		return readByName;
	}

	public void setReadByName(String readByName) {
		this.readByName = readByName;
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
