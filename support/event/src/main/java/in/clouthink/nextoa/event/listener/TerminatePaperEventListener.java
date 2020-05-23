package in.clouthink.nextoa.event.listener;

import in.clouthink.nextoa.model.PaperAction;
import in.clouthink.nextoa.service.PaperInnerService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class TerminatePaperEventListener implements EventListener<PaperAction>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(PaperAction paperAction) {
		paperInnerService.handleTerminatePaperAction(paperAction);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("paper").register(PaperAction.TERMINATE_ACTION, this);
	}

}
