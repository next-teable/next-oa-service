package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.PaperActionType;

import java.util.List;

/**
 *
 */
public interface StartPaperRequest extends AbstractPaperRequest {

	//允许的操作
	List<PaperActionType> getDisabledActions();

}
