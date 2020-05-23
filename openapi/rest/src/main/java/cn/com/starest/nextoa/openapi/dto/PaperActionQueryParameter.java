package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.PaperActionType;
import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.model.dtr.PaperActionQueryRequest;

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
