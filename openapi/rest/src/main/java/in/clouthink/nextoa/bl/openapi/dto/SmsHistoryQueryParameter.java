package in.clouthink.nextoa.bl.openapi.dto;


import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.request.SmsHistoryQueryRequest;
import in.clouthink.nextoa.shared.domain.params.DateRangedQueryParameter;
import io.swagger.annotations.ApiModel;

@ApiModel
public class SmsHistoryQueryParameter extends DateRangedQueryParameter implements SmsHistoryQueryRequest {

	private String cellphone;

	private SmsHistory.SmsCategory category;

	private SmsHistory.SmsStatus status;

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public SmsHistory.SmsCategory getCategory() {
		return category;
	}

	public void setCategory(SmsHistory.SmsCategory category) {
		this.category = category;
	}

	public SmsHistory.SmsStatus getStatus() {
		return status;
	}

	public void setStatus(SmsHistory.SmsStatus status) {
		this.status = status;
	}
}
