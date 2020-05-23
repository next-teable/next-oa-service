package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.PaperActionType;

import java.util.List;

/**
 *
 */
public interface StartPaperRequest extends AbstractPaperRequest {

	//允许的操作
	List<PaperActionType> getDisabledActions();

}
