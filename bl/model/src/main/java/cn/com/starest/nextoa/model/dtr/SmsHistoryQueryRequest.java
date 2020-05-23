package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.SmsHistory;

/**
 */
public interface SmsHistoryQueryRequest extends DateRangedQueryRequest {

	String getCellphone();

	SmsHistory.SmsCategory getCategory();

	SmsHistory.SmsStatus getStatus();

}
