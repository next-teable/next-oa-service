package cn.com.starest.nextoa.event.listener;

import cn.com.starest.nextoa.model.dtr.ReadNoticeEvent;
import cn.com.starest.nextoa.service.NoticeService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ReadNoticeEventListener implements EventListener<ReadNoticeEvent>, InitializingBean {

	@Autowired
	private NoticeService noticeService;

	@Override
	public void onEvent(ReadNoticeEvent event) {
		noticeService.markNoticeAsRead(event.getNotice(), event.getUser());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm().register(ReadNoticeEvent.EVENT_NAME, this);
	}

}
