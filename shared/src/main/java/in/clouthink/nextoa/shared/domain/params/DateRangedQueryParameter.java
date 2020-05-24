package in.clouthink.nextoa.shared.domain.params;


import com.fasterxml.jackson.annotation.JsonFormat;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 */
@ApiModel
public class DateRangedQueryParameter extends PageQueryParameter implements DateRangedQueryRequest {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
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
