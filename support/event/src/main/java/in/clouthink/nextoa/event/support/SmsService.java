package in.clouthink.nextoa.event.support;

public interface SmsService {

	void sendPendingPaperMessage(String cellphone, String messageId, String sender, String title);

}
