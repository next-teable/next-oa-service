package in.clouthink.nextoa.event.listener;

import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import in.clouthink.nextoa.bl.request.MessageNotifyRequest;
import in.clouthink.nextoa.event.support.SmsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dz
 */
@Component
public class NotifyMessageEventListener implements EventListener<List<MessageNotifyRequest>>, InitializingBean {

	@Autowired
	private SmsService smsService;

	@Override
	public void onEvent(List<MessageNotifyRequest> event) {
		if (event == null || event.isEmpty()) {
			return;
		}
		event.stream()
			 .forEach(request -> smsService.sendPendingPaperMessage(request.getCellphone(),
																	request.getMessageId(),
																	request.getMessageSender(),
																	request.getMessageTitle()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("sms").register(MessageNotifyRequest.MESSAGE_NOTIFY, this);
	}

}
