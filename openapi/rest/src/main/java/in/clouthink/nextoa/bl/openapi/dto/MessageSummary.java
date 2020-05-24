package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.BizRefType;
import in.clouthink.nextoa.bl.model.Message;
import in.clouthink.nextoa.bl.model.MessageStatus;
import in.clouthink.nextoa.bl.model.Paper;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 *
 */
@ApiModel
public class MessageSummary {

	static void convert(Message message, Paper paper, MessageSummary result) {
		BeanUtils.copyProperties(message, result, "sender", "receiver", "attributes");
		if (message.getSender() != null) {
			result.setSentById(message.getSender().getId());
			result.setSentByName(message.getSender().getUsername());
		}
		result.setUrgent(paper.isUrgent());
		result.setPaperAuthorName(paper.getCreatedBy().getUsername());
		if (paper.getLatestPaperAction() != null) {
			result.setLatestHandlerName(paper.getLatestPaperAction().getCreatedBy().getUsername());
			result.setLatestHandledAt(paper.getLatestPaperAction().getCreatedAt());
		}
	}

	public static MessageSummary from(Message message, Paper paper) {
		if (message == null) {
			return null;
		}
		MessageSummary result = new MessageSummary();
		convert(message, paper, result);
		return result;
	}

	private String id;

	// 业务主体（公文）的类型
	@Indexed
	private BizRefType bizRefType;

	// 业务主体（公文）的引用
	@Indexed
	private String bizRefId;

	private String category;

	private String title;

	// 消息接受时间（也是消息创建的时间）
	private Date receivedAt;

	// 创建人（指公文最开始的创建人）
	private String paperAuthorName;

	// 发送人（上一个处理人）
	private String sentById;

	// 发送人（上一个处理人）
	private String sentByName;

	// 最近处理人（回复,转发均可）
	// from biz
	private String latestHandlerName;

	// 最近处理时间（回复,转发均可）
	// from biz
	private Date latestHandledAt;

	// 任务状态
	private MessageStatus status;

	//是否已收藏
	private boolean favorite = false;

	//是否已读
	private boolean read = false;

	//是否紧急
	private boolean urgent = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BizRefType getBizRefType() {
		return bizRefType;
	}

	public void setBizRefType(BizRefType bizRefType) {
		this.bizRefType = bizRefType;
	}

	public String getBizRefId() {
		return bizRefId;
	}

	public void setBizRefId(String bizRefId) {
		this.bizRefId = bizRefId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPaperAuthorName() {
		return paperAuthorName;
	}

	public void setPaperAuthorName(String paperAuthorName) {
		this.paperAuthorName = paperAuthorName;
	}

	public String getSentById() {
		return sentById;
	}

	public void setSentById(String sentById) {
		this.sentById = sentById;
	}

	public String getSentByName() {
		return sentByName;
	}

	public void setSentByName(String sentByName) {
		this.sentByName = sentByName;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getLatestHandlerName() {
		return latestHandlerName;
	}

	public void setLatestHandlerName(String latestHandlerName) {
		this.latestHandlerName = latestHandlerName;
	}

	public Date getLatestHandledAt() {
		return latestHandledAt;
	}

	public void setLatestHandledAt(Date latestHandledAt) {
		this.latestHandledAt = latestHandledAt;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
}
