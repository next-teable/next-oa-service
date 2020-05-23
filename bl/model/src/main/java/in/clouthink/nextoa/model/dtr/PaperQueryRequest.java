package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.PaperStatus;

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
