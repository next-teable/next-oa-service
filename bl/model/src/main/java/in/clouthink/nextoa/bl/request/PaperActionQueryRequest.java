package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.PaperActionType;
import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 *
 */
public interface PaperActionQueryRequest extends DateRangedQueryRequest {

	PaperActionType[] getPaperActionTypes();

}
