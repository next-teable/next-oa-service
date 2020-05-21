package cn.com.starest.nextoa.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Document(collection = "PaperActions")
public class PaperAction extends StringIdentifier {

	public static final String REPLY_ACTION = "REPLY_ACTION";
	public static final String START_ACTION = "START_ACTION";
	public static final String FORWARD_ACTION = "FORWARD_ACTION";
	public static final String REVOKE_ACTION = "REVOKE_ACTION";
	public static final String END_ACTION = "END_ACTION";
	public static final String TERMINATE_ACTION = "TERMINATE_ACTION";

	private String content;

	@Indexed
	@DBRef(lazy = true)
	private Paper paper;

	private String previousActionId;

	private PaperActionType type;

	private String paperContent;

	//  null is not allowed if type is start,reply,forward
	private List<Receiver> toReceivers;

	private List<Receiver> ccReceivers;

	// set the value only when type is start
	private List<PaperActionType> allowedActions;

	private boolean failed;

	private String errorMessage;

	@Indexed
	@DBRef
	private User createdBy;

	private Date createdAt;

	private Date modifiedAt;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public String getPreviousActionId() {
		return previousActionId;
	}

	public void setPreviousActionId(String previousActionId) {
		this.previousActionId = previousActionId;
	}

	public PaperActionType getType() {
		return type;
	}

	public void setType(PaperActionType type) {
		this.type = type;
	}

	public String getPaperContent() {
		return paperContent;
	}

	public void setPaperContent(String paperContent) {
		this.paperContent = paperContent;
	}

	public List<Receiver> getToReceivers() {
		return toReceivers;
	}

	public void setToReceivers(List<Receiver> toReceivers) {
		this.toReceivers = toReceivers;
	}

	public List<Receiver> getCcReceivers() {
		return ccReceivers;
	}

	public void setCcReceivers(List<Receiver> ccReceivers) {
		this.ccReceivers = ccReceivers;
	}

	public List<PaperActionType> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<PaperActionType> allowedActions) {
		this.allowedActions = allowedActions;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
