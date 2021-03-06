package in.clouthink.nextoa.event.listener;

import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import in.clouthink.nextoa.bl.model.PaperAction;
import in.clouthink.nextoa.bl.service.PaperInnerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class RevokePaperEventListener implements EventListener<PaperAction>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(PaperAction revokePaperAction) {
		paperInnerService.handleRevokePaperAction(revokePaperAction);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("paper").register(PaperAction.REVOKE_ACTION, this);
	}

}
