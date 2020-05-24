package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.PaperActionType;
import in.clouthink.nextoa.bl.request.PaperActionQueryRequest;
import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;
import in.clouthink.nextoa.shared.domain.request.PageQueryRequest;

/**
 *
 */
public class PaperActionQueryParameter extends DateRangedQueryParameter implements PaperActionQueryRequest {

	private PaperActionType[] paperActionTypes;

	public PaperActionQueryParameter() {
	}

	public PaperActionQueryParameter(PageQueryRequest queryRequest) {
		setStart(queryRequest.getStart());
		setLimit(queryRequest.getLimit());
	}

	public PaperActionQueryParameter(DateRangedQueryRequest queryRequest) {
		setStart(queryRequest.getStart());
		setLimit(queryRequest.getLimit());
		setBeginDate(queryRequest.getBeginDate());
		setEndDate(queryRequest.getEndDate());
	}

	@Override
	public PaperActionType[] getPaperActionTypes() {
		return paperActionTypes;
	}

	public void setPaperActionTypes(PaperActionType... paperActionTypes) {
		this.paperActionTypes = paperActionTypes;
	}
}
