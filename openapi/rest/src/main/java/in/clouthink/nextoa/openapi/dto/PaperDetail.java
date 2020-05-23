package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.PaperActionType;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 *
 */
@ApiModel
public class PaperDetail extends PaperSummary {

	public static PaperDetail from(Paper paper) {
		if (paper == null) {
			return null;
		}
		PaperDetail result = new PaperDetail();
		convert(paper, result);
		result.setContent(paper.getContent());
		result.setAllowedActions(paper.getAllowedActions());
		return result;
	}

	private String content;

	private List<PaperActionType> allowedActions;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<PaperActionType> getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(List<PaperActionType> allowedActions) {
		this.allowedActions = allowedActions;
	}

}
