package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.PaperStatus;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

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
