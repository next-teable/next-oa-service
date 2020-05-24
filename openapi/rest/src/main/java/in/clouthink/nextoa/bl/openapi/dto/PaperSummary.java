package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.model.PaperStatus;
import in.clouthink.nextoa.bl.model.PaperType;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@ApiModel
public class PaperSummary {

	static void convert(Paper paper, PaperSummary result) {
		result.setId(paper.getId());
		result.setType(paper.getType());
		result.setTitle(paper.getTitle());
		result.setCreatorId(paper.getCreatedBy().getId());
		result.setCreatorName(paper.getCreatedBy().getUsername());
		if (paper.getCreatedBy().getOrganizations() != null) {
			result.setCreatorOrganizations(paper.getCreatedBy()
												.getOrganizations()
												.stream()
												.map(SimpleOrganization::from)
												.collect(Collectors.toList()));
		}

		result.setCreatedAt(paper.getCreatedAt());
		result.setStatus(paper.getStatus());
		result.setCategory(paper.getCategory());
		result.setUrgent(paper.isUrgent());
		result.setBusinessComplete(paper.isBusinessComplete());
		PaperAction paperAction = paper.getLatestPaperAction();

		if (paperAction != null) {
			result.setHandlerId(paperAction.getCreatedBy().getId());
			result.setHandlerName(paperAction.getCreatedBy().getUsername());
			result.setHandledAt(paperAction.getCreatedAt());
		}
		else {
			result.setHandlerName(paper.getCreatedBy().getUsername());
			result.setHandledAt(paper.getCreatedAt());
		}
	}

	public static PaperSummary from(Paper paper) {
		if (paper == null) {
			return null;
		}
		PaperSummary result = new PaperSummary();
		convert(paper, result);
		return result;
	}

	private String id;

	private PaperType type;

	private String category;

	private String title;

	private String creatorId;

	private String creatorName;

	private List<SimpleOrganization> creatorOrganizations;

	private Date createdAt;

	private String handlerId;

	private String handlerName;

	private Date handledAt;

	private PaperStatus status;

	// 是否已收藏
	private boolean favorite = false;

	// 是否已读
	private boolean read = false;

	// 是否紧急
	private boolean urgent = false;

	// （如果带业务,业务是否完成）
	private boolean businessComplete = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PaperType getType() {
		return type;
	}

	public void setType(PaperType type) {
		this.type = type;
	}

	public String getHandlerId() {
		return handlerId;
	}

	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Date getHandledAt() {
		return handledAt;
	}

	public void setHandledAt(Date handledAt) {
		this.handledAt = handledAt;
	}

	public PaperStatus getStatus() {
		return status;
	}

	public void setStatus(PaperStatus status) {
		this.status = status;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public boolean isBusinessComplete() {
		return businessComplete;
	}

	public void setBusinessComplete(boolean businessComplete) {
		this.businessComplete = businessComplete;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<SimpleOrganization> getCreatorOrganizations() {
		return creatorOrganizations;
	}

	public void setCreatorOrganizations(List<SimpleOrganization> creatorOrganizations) {
		this.creatorOrganizations = creatorOrganizations;
	}
}
