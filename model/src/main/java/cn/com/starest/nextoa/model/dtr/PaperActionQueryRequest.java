package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.PaperActionType;

/**
 *
 */
public interface PaperActionQueryRequest extends DateRangedQueryRequest {

	PaperActionType[] getPaperActionTypes();

}
