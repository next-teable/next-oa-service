package cn.com.starest.nextoa.event.support;

public interface EmailService {

	void sendPendingPaperEmail(String username, String messageId, String title, String paperSender);

}
