package in.clouthink.nextoa.event.listener;

import in.clouthink.nextoa.model.dtr.ReadPaperEvent;
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
public class ReadPaperEventListener implements EventListener<ReadPaperEvent>, InitializingBean {

	@Autowired
	private PaperInnerService paperInnerService;

	@Override
	public void onEvent(ReadPaperEvent event) {
		paperInnerService.markPaperAsRead(event.getPaper(), event.getUser());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm().register(ReadPaperEvent.EVENT_NAME, this);
	}
}