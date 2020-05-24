package in.clouthink.nextoa.event.listener;

import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import in.clouthink.nextoa.bl.request.ReadNewsEvent;
import in.clouthink.nextoa.bl.service.NewsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@author dz
 */
@Component
public class ReadNewsEventListener implements EventListener<ReadNewsEvent>, InitializingBean {

	@Autowired
	private NewsService newsService;

	@Override
	public void onEvent(ReadNewsEvent event) {
		newsService.markNewsAsRead(event.getNews(), event.getUser());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm().register(ReadNewsEvent.EVENT_NAME, this);
	}
}
