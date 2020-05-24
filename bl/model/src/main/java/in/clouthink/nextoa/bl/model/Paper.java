package in.clouthink.nextoa.bl.model;

import com.google.common.hash.Hashing;
import in.clouthink.nextoa.shared.domain.model.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * 公文
 */
@Document(collection = "Papers")
public class Paper extends StringIdentifier {

	public static final String crc32(String content) {
		return Hashing.crc32().hashString(content, Charset.forName("UTF-8")).toString();
	}

	public static final boolean isEditAllowed(Paper paper) {
		if (paper == null) {
			return false;
		}
		if (paper.getAllowedActions() == null || paper.getAllowedActions().isEmpty()) {
			return false;
		}
		return paper.getAllowedActions().contains(PaperActionType.EDIT);
	}

	public static final boolean isPrintAllowed(Paper paper) {
		if (paper == null) {
			return false;
		}
		if (paper.getAllowedActions() == null || paper.getAllowedActions().isEmpty()) {
			return false;
		}
		return paper.getAllowedActions().contains(PaperActionType.PRINT);
	}

	public static final boolean isForwardAllowed(Paper paper) {
		if (paper == null) {
			return false;
		}
		if (paper.getAllowedActions() == null || paper.getAllowedActions().isEmpty()) {
			return false;
		}
		return paper.getAllowedActions().contains(PaperActionType.FORWARD);
	}

	public static final boolean isCopyAllowed(Paper paper) {
		if (paper == null) {
			return false;
		}
		if (paper.getAllowedActions() == null || paper.getAllowedActions().isEmpty()) {
			return false;
		}
		return paper.getAllowedActions().contains(PaperActionType.COPY);
	}

	@Indexed
	private String title;

	@Indexed
	private PaperType type;

	@Indexed
	private String category;

	private boolean urgent;

	private String content;

	private String contentCrc32;

	@Indexed
	private PaperStatus status;

	private List<PaperActionType> allowedActions;

	// 第一动作: 发起人提交,让公文进入流转
	@DBRef
	private PaperAction startPaperAction;

	// 最近一个处理人的动作: 其他人回复或转发,自己撤回
	// 如果为空,则取startPaperAction
	@DBRef
	private PaperAction latestPaperAction;

	private int readCounter;

	@Indexed
	@DBRef
	private User createdBy;

	// 创建时间,对应草稿状态
	private Date createdAt;

	// 创建时间,对应草稿状态
	private Date modifiedAt;

	// 流转时间,对应流转状态
	private Date startedAt;

	// 撤回时间,对应撤回状态
	private Date revokedAt;

	private int version = 0;

	private boolean businessComplete = false;

	public PaperType getType() {
		return type;
	}

	public void setType(PaperType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

    public void setContent(String content) {
        if (content != null) {
            this.content = content;
            this.contentCrc32 = Paper.crc32(content);
        }
    }

	public String getContentCrc32() {
		return contentCrc32;
	}

	public void setContentCrc32(String contentCrc32) {
		this.contentCrc32 = contentCrc32;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public PaperStatus getStatus() {
		return status;
	}

	public void setStatus(PaperStatus status) {
		this.status = status;
	}

	public List<PaperActionType> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<PaperActionType> allowedActions) {
		this.allowedActions = allowedActions;
	}

	public PaperAction getStartPaperAction() {
		return startPaperAction;
	}

	public void setStartPaperAction(PaperAction startPaperAction) {
		this.startPaperAction = startPaperAction;
	}

	public PaperAction getLatestPaperAction() {
		return latestPaperAction;
	}

	public void setLatestPaperAction(PaperAction latestPaperAction) {
		this.latestPaperAction = latestPaperAction;
	}

	public int getReadCounter() {
		return readCounter;
	}

	public void setReadCounter(int readCounter) {
		this.readCounter = readCounter;
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

	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public Date getRevokedAt() {
		return revokedAt;
	}

	public void setRevokedAt(Date revokedAt) {
		this.revokedAt = revokedAt;
	}

	public boolean isBusinessComplete() {
		return businessComplete;
	}

	public void setBusinessComplete(boolean businessComplete) {
		this.businessComplete = businessComplete;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


}
