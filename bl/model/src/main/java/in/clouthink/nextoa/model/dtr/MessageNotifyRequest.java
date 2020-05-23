package in.clouthink.nextoa.model.dtr;

/**
 */
public interface MessageNotifyRequest {

	String MESSAGE_NOTIFY = "MESSAGE_NOTIFY";

	String getCellphone();

	String getMessageId();

	String getMessageSender();

	String getMessageTitle();

}
