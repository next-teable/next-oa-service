package cn.com.starest.nextoa.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Transition : the same id as current paper action
 */
@Document(collection = "PaperTransitions")
public class PaperTransition extends StringIdentifier {

	public static boolean isUserParticipated(PaperTransition paperTransition, User user) {
		List<String> participantIds = paperTransition.getParticipantIds();
		if (participantIds == null || participantIds.isEmpty()) {
			return false;
		}
		boolean result = false;
		for (String participantId : participantIds) {
			if (user.getId().equalsIgnoreCase(participantId)) {
				result = true;
			}
		}
		return result;
	}

	@Indexed
	@DBRef(lazy = true)
	private Paper paper;

	private PaperActionType paperActionType;
	//
	private String currentActionId;

	private String previousActionId;

	private String nextActionId;

	/**
	 * The merged users(to & cc) of currentAction
	 */
	private List<String> participantIds;

	private String creatorId;

	@DBRef(lazy = true)
	private User createdBy;

	private Date createdAt;

	/**
	 * The transition map processed flag ( please don't persist to backend mongodb)
	 */
	@Transient
	private boolean processed;

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public PaperActionType getPaperActionType() {
		return paperActionType;
	}

	public void setPaperActionType(PaperActionType paperActionType) {
		this.paperActionType = paperActionType;
	}

	public String getCurrentActionId() {
		return currentActionId;
	}

	public void setCurrentActionId(String currentActionId) {
		this.currentActionId = currentActionId;
	}

	public String getPreviousActionId() {
		return previousActionId;
	}

	public void setPreviousActionId(String previousActionId) {
		this.previousActionId = previousActionId;
	}

	public String getNextActionId() {
		return nextActionId;
	}

	public void setNextActionId(String nextActionId) {
		this.nextActionId = nextActionId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<String> getParticipantIds() {
		return participantIds;
	}

	public void setParticipantIds(List<String> participantIds) {
		this.participantIds = participantIds;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
