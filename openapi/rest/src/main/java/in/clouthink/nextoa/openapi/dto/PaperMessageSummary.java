package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.MessageStatus;
import io.swagger.annotations.ApiModel;

import java.util.Date;


/**
 */
@ApiModel
public class PaperMessageSummary {

	static void convert(Message message, PaperMessageSummary result) {
		result.setId(message.getId());
		result.setStatus(message.getStatus());
		result.setReceivedAt(message.getReceivedAt());
		result.setProcessedAt(message.getModifiedAt());
		if (message.getSender() != null) {
			result.setSenderId(message.getSender().getId());
			result.setSenderName(message.getSender().getUsername());
		}
		if (message.getReceiver() != null) {
			result.setReceiverId(message.getReceiver().getId());
			result.setReceiverName(message.getReceiver().getUsername());
		}
	}

	public static PaperMessageSummary from(Message message) {
		if (message == null) {
			return null;
		}
		PaperMessageSummary result = new PaperMessageSummary();
		convert(message, result);
		return result;
	}

	private String id;

	private String receiverId;

	private String receiverName;

	private String senderId;

	private String senderName;

	private Date receivedAt;

	private Date processedAt;

	private MessageStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public Date getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(Date processedAt) {
		this.processedAt = processedAt;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}
}
