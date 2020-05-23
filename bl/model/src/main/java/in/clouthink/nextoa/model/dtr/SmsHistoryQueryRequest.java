package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.SmsHistory;

/**
 */
public interface SmsHistoryQueryRequest extends DateRangedQueryRequest {

	String getCellphone();

	SmsHistory.SmsCategory getCategory();

	SmsHistory.SmsStatus getStatus();

}
