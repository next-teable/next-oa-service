package in.clouthink.nextoa.event.listener;

import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.model.PaperTransitionRequest;
import in.clouthink.nextoa.bl.service.PaperInnerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ForwardPaperEventListener implements EventListener<PaperTransitionRequest>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(PaperTransitionRequest request) {
		paperInnerService.handleForwardPaperAction(request.getFromAction(), request.getToAction());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("paper").register(PaperAction.FORWARD_ACTION, this);
	}

}
