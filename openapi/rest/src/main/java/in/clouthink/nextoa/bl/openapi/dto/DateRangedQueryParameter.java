package in.clouthink.nextoa.bl.openapi.dto;


import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 */
@ApiModel
public class DateRangedQueryParameter extends PageQueryParameter implements DateRangedQueryRequest {

	private Date beginDate;

	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
