package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.PaperActionType;

/**
 *
 */
public interface PaperActionQueryRequest extends DateRangedQueryRequest {

	PaperActionType[] getPaperActionTypes();

}
