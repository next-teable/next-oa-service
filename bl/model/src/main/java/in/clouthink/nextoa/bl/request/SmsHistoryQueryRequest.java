package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 */
public interface SmsHistoryQueryRequest extends DateRangedQueryRequest {

	String getCellphone();

	SmsHistory.SmsCategory getCategory();

	SmsHistory.SmsStatus getStatus();

}
