package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.PaperActionType;
import cn.com.starest.nextoa.model.Receiver;

import java.util.List;

/**
 *
 */
public interface StartPaperRequest extends AbstractPaperRequest {

	//允许的操作
	List<PaperActionType> getDisabledActions();

}
