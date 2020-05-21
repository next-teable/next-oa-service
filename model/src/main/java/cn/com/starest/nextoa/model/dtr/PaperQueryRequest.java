package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.PaperStatus;

/**
 *
 */
public interface PaperQueryRequest extends DateRangedQueryRequest {

	enum IncludeOrExcludeStatus {
		INCLUDE , EXCLUDE
	}

	String getCategory();

	String getTitle();

	PaperStatus getPaperStatus();

	Boolean getUrgent();

	String getCreatorId();

	String getCreatorUsername();

}
