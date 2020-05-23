package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.PaperActionType;
import in.clouthink.nextoa.model.dtr.DateRangedQueryRequest;
import in.clouthink.nextoa.model.dtr.PageQueryRequest;
import in.clouthink.nextoa.model.dtr.PaperActionQueryRequest;

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
