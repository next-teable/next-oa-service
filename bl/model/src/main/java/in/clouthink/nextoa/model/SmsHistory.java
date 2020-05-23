package in.clouthink.nextoa.model;

import in.clouthink.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author dz
 */
@Document(collection = "SmsHistories")
public class SmsHistory extends StringIdentifier {

	public enum SmsCategory {
		PENDING_PAPER_MESSAGE;
	}

	public enum SmsStatus {
		SENT, FAILED;
	}

	private String cellphone;

	private String message;

	private Date createdAt;

	private SmsCategory category;

	private SmsStatus status;

	private String failureReason;

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public SmsCategory getCategory() {
		return category;
	}

	public void setCategory(SmsCategory category) {
		this.category = category;
	}

	public SmsStatus getStatus() {
		return status;
	}

	public void setStatus(SmsStatus status) {
		this.status = status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
}
