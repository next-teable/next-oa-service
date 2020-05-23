package in.clouthink.nextoa.event.listener;

import in.clouthink.nextoa.model.dtr.DownloadAttachmentEvent;
import in.clouthink.nextoa.service.AttachmentService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@author dz
 */
@Component
public class DownloadAttachmentEventListener implements EventListener<DownloadAttachmentEvent>, InitializingBean {

	@Autowired
	private AttachmentService attachmentService;

	@Override
	public void onEvent(DownloadAttachmentEvent event) {
		attachmentService.markAttachmentAsDownloaded(event.getAttachment(), event.getUser());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm().register(DownloadAttachmentEvent.EVENT_NAME, this);
	}

}
